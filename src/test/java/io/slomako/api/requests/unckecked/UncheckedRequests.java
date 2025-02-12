package io.slomako.api.requests.unckecked;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class UncheckedRequests {

    private final UncheckedUser userRequest;
    private final UncheckedProject projectRequest;
    private final UncheckedBuildConfig buildConfigRequest;

    public UncheckedRequests(RequestSpecification spec) {
        this.userRequest = new UncheckedUser(spec);
        this.buildConfigRequest = new UncheckedBuildConfig(spec);
        this.projectRequest = new UncheckedProject(spec);
    }
}
