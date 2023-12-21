package io.slomako.api.generators;

import io.slomako.api.models.*;

import java.util.Collections;

public class TestDataGenerator {

    public static TestData generate() {
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

        var buildType = BuildType.builder()
                .id(RandomData.randomString())
                .name(RandomData.randomString())
                .project(newProjectDescription)
                .build();

        return TestData.builder().user(user).project(newProjectDescription).buildType(buildType).build();
    }

    public static Roles generateRoles(io.slomako.api.enums.Role role, String scope) {
        return Roles.builder().role
                (Collections.singletonList(Role.builder().roleId(role.getText())
                        .scope(scope).build())).build();
    }
}
