package org.example.utils;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.example.drivers.DriverContainer;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class ExtendedListener extends ReportPortalTestNGListener {
    @Override
    public void onTestFailure(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            if (DriverContainer.getDriver() instanceof TakesScreenshot) {
                String screenshot = ((TakesScreenshot) DriverContainer.getDriver()).getScreenshotAs(OutputType.BASE64);
                LoggingUtils.logBase64(screenshot, "Invalid page");
            }

            super.onTestFailure(testResult);
        }
    }
}
