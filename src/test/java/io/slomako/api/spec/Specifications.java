package io.slomako.api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.slomako.api.config.Config;
import io.slomako.api.models.User;

public class Specifications {

    private static Specifications specifications;

    private Specifications() {
    }

    public static Specifications getInstance() {
        if (specifications == null) {
            specifications = new Specifications();
        }
        return specifications;
    }

    private RequestSpecBuilder requestSpecBuilder() {
        var requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("http://" + Config.getProperty("host"));
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.setAccept(ContentType.JSON);
        return requestBuilder;
    }

    public RequestSpecification unAuthSpec() {
        var requestBuilder = requestSpecBuilder();
        return requestBuilder.build();
    }

    public RequestSpecification authSpec(User user) {
        var requestBuilder = requestSpecBuilder();
        requestBuilder.setBaseUri("http://" + user.getUsername() + ":" + user.getPassword() +"@" + Config.getProperty("host"));
        return requestBuilder.build();
    }
}
