package io.slomako.api.generators;

import io.slomako.api.models.NewProjectDescription;
import io.slomako.api.models.User;
import io.slomako.api.requests.unckecked.UncheckedProject;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestData {

    private User user;
    private NewProjectDescription project;

    public void delete() {
        new UncheckedProject(user).delete(project.getId());
    }
}
