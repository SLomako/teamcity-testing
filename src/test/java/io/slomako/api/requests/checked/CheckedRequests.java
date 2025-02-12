package io.slomako.api.requests.checked;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class CheckedRequests {

    private final CheckedUser userRequest;
    private final CheckedProject projectRequest;
    private final CheckedBuildConfig buildConfigRequest;

    public CheckedRequests(RequestSpecification spec) {
        this.userRequest = new CheckedUser(spec);
        this.buildConfigRequest = new CheckedBuildConfig(spec);
        this.projectRequest = new CheckedProject(spec);
    }
}
