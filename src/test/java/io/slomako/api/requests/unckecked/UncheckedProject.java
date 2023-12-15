package io.slomako.api.requests.unckecked;

import io.restassured.response.Response;
import io.slomako.api.models.NewProjectDescription;
import io.slomako.api.models.User;
import io.slomako.api.requests.CrudInterface;
import io.slomako.api.spec.Specifications;

import static io.restassured.RestAssured.given;

public class UncheckedProject implements CrudInterface {

    private static final String PROJECT_ENDPOINT = "/app/rest/projects";
    private final User user;

    public UncheckedProject(User user) {
        this.user = user;
    }

    @Override
    public Response create(Object obj) {
        return given()
                .spec(Specifications.getInstance().authSpec(user))
                .body(obj)
                .post(PROJECT_ENDPOINT);
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public Object update(String id, Object obj) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return given()
                .spec(Specifications.getInstance().authSpec(user))
                .delete("PROJECT_ENDPOINT" + "/id:" + id);
    }
}
