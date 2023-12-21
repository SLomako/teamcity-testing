package io.slomako.api.test;

import io.slomako.api.TestBase;
import io.slomako.api.generators.TestDataStorage;
import io.slomako.api.requests.checked.CheckedRequests;
import io.slomako.api.requests.unckecked.UncheckedRequests;
import io.slomako.api.spec.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBaseApi extends TestBase {

    public TestDataStorage testDataStorage;

    public CheckedRequests checkedWithSuperUser
            = new CheckedRequests(Specifications.getInstance().superUserSpec());

    public UncheckedRequests uncheckedWithSuperUser
            = new UncheckedRequests(Specifications.getInstance().superUserSpec());

    @BeforeMethod
    public void setupTest() {
        testDataStorage = TestDataStorage.getStorage();
    }

    @AfterMethod
    public void cleanTest() {
        testDataStorage.delete();
    }
}
