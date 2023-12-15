package io.slomako.api.requests.checked;

import io.restassured.specification.RequestSpecification;
import io.slomako.api.models.Project;
import io.slomako.api.requests.CrudInterface;
import io.slomako.api.requests.unckecked.UncheckedProject;
import org.apache.http.HttpStatus;

public class CheckedProject implements CrudInterface {

    private final RequestSpecification spec;

    public CheckedProject(RequestSpecification spec) {
        this.spec = spec;
    }

    @Override
    public Project create(Object obj) {
        return new UncheckedProject(spec)
                .create(obj)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Project get(String id) {
        return new UncheckedProject(spec)
                .get(id)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Object update(String id, Object obj) {
        return null;
    }

    @Override
    public String delete(String id) {
        return new UncheckedProject(spec)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}
