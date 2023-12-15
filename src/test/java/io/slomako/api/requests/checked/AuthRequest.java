package io.slomako.api.requests.checked;

import io.restassured.RestAssured;
import io.slomako.api.models.User;
import io.slomako.api.spec.Specifications;
import org.apache.http.HttpStatus;

public class AuthRequest {

    private User user;

    public AuthRequest(User user) {
        this.user = user;
    }

    public String getCsrfToken() {
        return RestAssured
                .given()
                .spec(Specifications.getInstance().authSpec(user))
                .get("/authenticationTest.html?csrf")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}
