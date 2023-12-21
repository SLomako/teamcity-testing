package io.slomako.api.requests.checked;

import io.restassured.specification.RequestSpecification;
import io.slomako.api.models.BuildType;
import io.slomako.api.requests.CrudInterface;
import io.slomako.api.requests.Request;
import io.slomako.api.requests.unckecked.UncheckedBuildConfig;
import org.apache.http.HttpStatus;

public class CheckedBuildConfig extends Request implements CrudInterface {

    public CheckedBuildConfig(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public BuildType create(Object obj) {
        return new UncheckedBuildConfig(spec).create(obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(BuildType.class);
    }

    @Override
    public BuildType get(String id) {
        return new UncheckedBuildConfig(spec).get(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(BuildType.class);
    }

    @Override
    public BuildType update(String id, Object obj) {
        return new UncheckedBuildConfig(spec).update(id, obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(BuildType.class);
    }

    @Override
    public String delete(String id) {
        return new UncheckedBuildConfig(spec).delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().asString();
    }
}
