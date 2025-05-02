package com.browserstack.perftest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;


public class TestDataFactory {

    @DataProvider(name = "testData", parallel = true)
    public Object[][] createTestData() {
        Object[][] data = new Object[50][1];
        for (int i = 0; i < 50; i++) {
            data[i][0] = i + 1; // Numbers 1 to 50
        }
        return data;
    }

    @Factory(dataProvider = "testData")
    public Object[] createInstances(int data) {
        return new Object[] {
                new MashReqAppPerf(data)
        };
    }
}