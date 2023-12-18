package io.slomako.api.test;

import io.slomako.api.models.Role;
import io.slomako.api.models.Roles;
import io.slomako.api.requests.checked.CheckedProject;
import io.slomako.api.requests.checked.CheckedUser;
import io.slomako.api.spec.Specifications;
import org.testng.annotations.Test;

import java.util.Arrays;

public class BuildConfigurationTest extends TestBaseApi {

    @Test()
    void buildConfigurationTest() {
        testData.getUser().setRoles(Roles.builder()
                .role(Arrays.asList(Role.builder()
                        .roleId("SYSTEM_ADMIN")
                        .scope("g")
                        .build()))
                .build());

        new CheckedUser(Specifications.getInstance().superUserSpec()).create(testData.getUser());

        var project = new CheckedProject(Specifications.getInstance().authSpec(testData.getUser())).create(testData.getProject());

        softAssert.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }
}
