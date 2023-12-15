package io.slomako.api;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected SoftAssertions softAssert;

    @BeforeMethod
    public void beforeTest() {
        softAssert = new SoftAssertions();
    }

    @AfterMethod
    public void afterTest() {
        softAssert.assertAll();
    }
}
