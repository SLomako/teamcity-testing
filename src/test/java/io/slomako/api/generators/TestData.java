package io.slomako.api.generators;

import io.slomako.api.models.NewProjectDescription;
import io.slomako.api.models.User;
import io.slomako.api.requests.unckecked.UncheckedProject;
import io.slomako.api.spec.Specifications;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestData {

    private User user;
    private NewProjectDescription project;

    public void delete() {
        new UncheckedProject(Specifications.getInstance().authSpec(user)).delete(project.getId());
    }
}
