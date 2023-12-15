package io.slomako.api.requests.unckecked;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.slomako.api.requests.CrudInterface;

import static io.restassured.RestAssured.given;

public class UncheckedProject implements CrudInterface {

    private static final String PROJECT_ENDPOINT = "/app/rest/projects";
    private final RequestSpecification spec;

    public UncheckedProject(RequestSpecification spec) {
        this.spec = spec;
    }

    @Override
    public Response create(Object obj) {
        return given()
                .spec(spec)
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
                .spec(spec)
                .delete("PROJECT_ENDPOINT" + "/id:" + id);
    }
}
