package io.slomako.api.generators;

import io.slomako.api.models.*;

import java.util.Collections;

public class TestDataGenerator {

    public TestData generate() {
         var user = User.builder()
                    .username(RandomData.randomString())
                    .password(RandomData.randomString())
                    .email(RandomData.randomString() + "@gmail.com")
                    .roles(Roles.builder()
                            .role(Collections.singletonList(Role.builder()
                                    .roleId("SYSTEM_ADMIN")
                                    .scope("g")
                                    .build()))
                            .build())
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
