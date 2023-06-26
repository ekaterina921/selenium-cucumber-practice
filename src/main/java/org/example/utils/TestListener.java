package org.example.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;



public class TestListener implements ITestListener {


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        new Utilities().saveScreenshot();
    }

}

