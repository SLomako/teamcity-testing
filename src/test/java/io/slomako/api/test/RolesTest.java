package io.slomako.api.test;

import io.slomako.api.requests.unckecked.UncheckedProject;
import io.slomako.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.core.StringContains.containsString;

public class RolesTest extends TestBaseApi {

    @Test
    public void unauthorizedUser() {
        new UncheckedProject(Specifications.getInstance().unAuthSpec())
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(containsString("Authentication required"));

        new UncheckedProject(Specifications.getInstance().authSpec(testData.getUser()))
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(containsString("No project found by locator 'count:1,id:" + testData.getProject().getId() + "'"));
    }
}
