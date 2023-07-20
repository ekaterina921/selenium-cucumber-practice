package org.example.utils;

    import com.epam.reportportal.message.ReportPortalMessage;
    import com.epam.reportportal.testng.BaseTestNGListener;
    import lombok.extern.log4j.Log4j2;
    import org.apache.commons.io.FileUtils;
    import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
    import org.openqa.selenium.WebDriver;
    import org.testng.ITestContext;
    import org.testng.ITestResult;
    import java.io.File;
    import java.io.IOException;
    import java.util.concurrent.TimeUnit;

@Log4j2
public class ExtendedListener extends BaseTestNGListener {
    public ExtendedListener() {
        super(new ParamOverrideTestNgService());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("======================================== STARTING TEST '%s' ========================================%n", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("======================================== FINISHED TEST '%s' Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(String.format("======================================== FAILED TEST '%s' Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        log.info(getReportPortalMessage(iTestResult));
        Utilities.takeScreenshot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("======================================== SKIPPING TEST '%s' ========================================%n", iTestResult.getName()));
        Utilities.takeScreenshot(iTestResult);
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    public ReportPortalMessage getReportPortalMessage(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("target/screenshots/" + Utilities.getCurrentTimeAsString() + ".png"));
            String reportPortalMessage = "ERROR screenshot";
            return new ReportPortalMessage(screenshot, reportPortalMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}