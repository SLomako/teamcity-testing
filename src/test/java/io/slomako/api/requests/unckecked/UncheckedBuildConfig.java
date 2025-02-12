package io.slomako.api.requests.unckecked;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.slomako.api.requests.CrudInterface;
import io.slomako.api.requests.Request;

import static io.restassured.RestAssured.given;

public class UncheckedBuildConfig extends Request implements CrudInterface {

    private static final String BUILD_CONFIG_ENDPOINT = "/app/rest/buildTypes";

    public UncheckedBuildConfig(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Response create(Object obj) {
        return given(spec).body(obj).post(BUILD_CONFIG_ENDPOINT);
    }

    @Override
    public Response get(String id) {
        return given()
                .spec(spec)
                .get(BUILD_CONFIG_ENDPOINT + "/id:" + id);
    }

    @Override
    public Response update(String id, Object obj) {
        return given()
                .spec(spec)
                .body(obj)
                .get(BUILD_CONFIG_ENDPOINT + "/id:" + id);
    }

    @Override
    public Response delete(String id) {
        return given(spec).delete(BUILD_CONFIG_ENDPOINT + "/id:" + id);
    }
}
