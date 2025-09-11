package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.google.common.io.Files;

import base.DriverSetup;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String browser = System.getProperty("browser", "default");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName() + " [" + browser + "]");
        test.set(extentTest);
        test.get().log(Status.INFO, "Test started on browser: " + browser);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

//    @Override
//    public void onTestFailure(ITestResult result) {
//        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
//        // Optionally, add screenshot here for failed test
//    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, result.getThrowable());

        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String basePath = System.getProperty("user.dir") + "/target/";
            String testName = result.getMethod().getMethodName();

            String htmlFile = basePath + testName + "_" + timestamp + ".html";
            String source = DriverSetup.getDriver().getPageSource();
            Files.write(Paths.get(htmlFile), source.getBytes(StandardCharsets.UTF_8));
            test.get().log(Status.INFO, "Page source saved: " + htmlFile);

            File screenshot = ((TakesScreenshot) DriverSetup.getDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotPath = basePath + testName + "_" + timestamp + ".png";
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            test.get().addScreenCaptureFromPath(screenshotPath);

            System.out.println("Saved HTML + Screenshot for failed test: " + testName);
        } catch (Exception e) {
            System.err.println("Could not save debug artifacts → " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Write results to report
    }
}