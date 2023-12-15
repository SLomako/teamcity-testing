package io.slomako.api.generators;

import io.slomako.api.models.NewProjectDescription;
import io.slomako.api.models.Project;
import io.slomako.api.models.User;
import org.testng.annotations.Test;

public class TestDataGenerator {

    public TestData generate() {

        var user = User.builder()
                .username("admin")
                .password("admin")
                .build();

        var newProjectDescription = NewProjectDescription.builder()
                .parentProject(Project.builder().locator("_Root").build())
                .name(RandomData.randomString())
                .id(RandomData.randomString())
                .copyAllAssociatedSettings(true)
                .build();

        return TestData.builder().user(user).project(newProjectDescription).build();
    }
}
