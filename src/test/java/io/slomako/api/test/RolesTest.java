package io.slomako.api.test;

import io.slomako.api.enums.Role;
import io.slomako.api.generators.TestDataGenerator;
import io.slomako.api.requests.checked.CheckedBuildConfig;
import io.slomako.api.requests.checked.CheckedProject;
import io.slomako.api.requests.unckecked.UncheckedBuildConfig;
import io.slomako.api.requests.unckecked.UncheckedProject;
import io.slomako.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.core.StringContains.containsString;

public class RolesTest extends TestBaseApi {

    @Test
    void unauthorizedUserShouldNotHaveRightToCreateProject() {

        var testData = testDataStorage.addTestData();

        new UncheckedProject(Specifications.getInstance().unAuthSpec())
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(containsString("Authentication required"));

        new UncheckedProject(Specifications.getInstance().superUserSpec())
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(containsString("No project found by locator 'count:1,id:" + testData.getProject().getId() + "'"));
    }

    @Test()
    void systemAdminShouldHaveRightsToCreateProject() {

        var testData = testDataStorage.addTestData();

        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN, "g"));

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        var project = new CheckedProject(Specifications.getInstance()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softAssert.assertThat(project.getId()).isEqualTo(testData.getProject().getId());
    }

    @Test()
    void projectAdminShouldHaveRightsToCreateBuildConfigToHisProject() {

        var testData = testDataStorage.addTestData();
        testData.getUser()
                .setRoles(TestDataGenerator.generateRoles(Role.PROJECT_ADMIN, "p:" + testData.getProject().getId()));

        checkedWithSuperUser.getProjectRequest()
                .create(testData.getProject());

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        var buildConfig = new CheckedBuildConfig(Specifications.getInstance().authSpec(testData.getUser()))
                .create(testData.getBuildType());

        softAssert.assertThat(buildConfig.getId()).isEqualTo(testData.getBuildType().getId());
    }

    @Test()
    void projectAdminShouldNotHaveRightsToCreateBuildConfigToAnotherProject() {

        var firstTestData = testDataStorage.addTestData();
        var secondTestData = testDataStorage.addTestData();

        checkedWithSuperUser.getProjectRequest().create(firstTestData.getProject());
        checkedWithSuperUser.getProjectRequest().create(secondTestData.getProject());

        firstTestData.getUser().setRoles(
                TestDataGenerator.generateRoles(Role.PROJECT_ADMIN, "p:" + firstTestData.getProject().getId()));

        checkedWithSuperUser.getUserRequest()
                .create(firstTestData.getUser());

        secondTestData.getUser().setRoles(
                TestDataGenerator.generateRoles(Role.PROJECT_ADMIN, "p:" + secondTestData.getProject().getId()));

        checkedWithSuperUser.getUserRequest()
                .create(secondTestData.getUser());

        new UncheckedBuildConfig(Specifications.getInstance().authSpec(secondTestData.getUser()))
                .create(firstTestData.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
