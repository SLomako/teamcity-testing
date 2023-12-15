package io.slomako.api.test;

import io.slomako.api.requests.checked.CheckedProject;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends TestBaseApi {

    @Test
    void buildConfigurationTest() {

        var project = new CheckedProject(testData.getUser()).create(testData.getProject());

        softAssert.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }
}
