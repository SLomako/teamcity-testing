package io.slomako.api.generators;

import java.util.ArrayList;
import java.util.List;

public class TestDataStorage {

    private static TestDataStorage testDataStorage;
    private final List<TestData> testDataList;

    private TestDataStorage() {
        this.testDataList = new ArrayList<>();
    }

    public static TestDataStorage getStorage() {
        if (testDataStorage == null) {
            testDataStorage = new TestDataStorage();
        }
        return testDataStorage;
    }

    public void delete() {
        testDataList.forEach(TestData::delete);
    }

    public TestData addTestData() {
        var testData = TestDataGenerator.generate();
        addTestData(testData);
        return testData;
    }

    public void addTestData(TestData testData) {
        getStorage().testDataList.add(testData);
    }
}
