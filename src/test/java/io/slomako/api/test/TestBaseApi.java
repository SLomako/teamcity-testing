package io.slomako.api.test;

import io.slomako.api.TestBase;
import io.slomako.api.generators.TestData;
import io.slomako.api.generators.TestDataGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBaseApi extends TestBase {

    public TestData testData;

    @BeforeMethod
    public void setupTest() {
        testData = new TestDataGenerator().generate();
    }

    @AfterMethod
    public void cleanTest() {
        testData.delete();
    }
}
