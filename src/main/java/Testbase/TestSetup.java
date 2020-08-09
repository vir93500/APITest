package Testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import reporting.ExtentManger;
import reporting.SoftAssert;
import utils.Utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

/**
 * Test setup class used to initialized report and commonly used utility.
 * Create and extent report, data class object, DB
 */
public class TestSetup {
    public static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> classlevelLogger = new ThreadLocal<>();
    protected static ThreadLocal<ExtentTest> testLevelLogger = new ThreadLocal<>();
    protected SoftAssert softAssert;
    protected static RequestBuilder requestBuilder;
    public static Properties properties;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        properties = new Properties();

        extent = ExtentManger.getExtent();
        try {
            Utils.moveReportToArchive();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        ExtentTest parent = extent.createTest(getClass().getSimpleName());
        classlevelLogger.set(parent);

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpBeforeMethod(Method method) {
        softAssert = new SoftAssert();
      //  requestBuilder = new RequestBuilder();

        String className = method.getDeclaringClass().toString();
        className = className.substring(className.lastIndexOf('.') + 1);
        String testDesc = getTestDescription(method);

        ExtentTest child = classlevelLogger.get().createNode(method.getName());
        testLevelLogger.set(child);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {

            String methodname = result.getMethod().getMethodName();
            String logTest = "<b>" + "Test Case:- " + methodname + " Passed" + "</b>";
            Markup m = MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
            logger().log(Status.PASS, m);
            extent.flush();
        } else if (result.getStatus() == ITestResult.SKIP) {

            String methodname = result.getMethod().getMethodName();
            String logTest = "<b>" + "Test Case:- " + methodname + " Skipped" + "</b>";
            Markup m = MarkupHelper.createLabel(logTest, ExtentColor.YELLOW);
            logger().log(Status.SKIP, m);
            extent.flush();
        } else if (result.getStatus() == ITestResult.FAILURE) {

            String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
            logger()
                    .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                            + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                            + " \n");


            String failureLogg = "This Test case got Failed";
            Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
            logger().log(Status.FAIL, m);
            extent.flush();
        }
        extent.flush();
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws IOException {
        Utils.createReport();

    }

    public static ExtentTest logger() {
        return testLevelLogger.get();
    }

    private String getTestDescription(Method caller) {
        Test testAnnotation = caller.getAnnotation(Test.class);
        if (testAnnotation != null) {
            return testAnnotation.description();
        }
        return "";
    }
}
