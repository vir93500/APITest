package Testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestSetup {

    protected static ExtentReports extent;
    protected static  ThreadLocal<ExtentTest> classLevelLogger = new ThreadLocal<ExtentTest>();
    protected static  ThreadLocal<ExtentTest> testLevelLogger = new ThreadLocal<ExtentTest>();
    protected static SoftAssert softAssert;
    protected static Properties properties;
    protected static RequestBuilder requestBuilder;

    @BeforeSuite
    public void beforeSuite() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("./src/main/resources/log4j.properties"));
        PropertyConfigurator.configure(props);
        properties = new Properties();
      //  extent = ExtentManager
    }
    @BeforeClass
    public void beforeClass()
    {
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void beforemethod(Method method)
    {

    }

    @AfterMethod
    public void afterMethod(ITestResult result)
    {
   if(result.getStatus()==ITestResult.SUCCESS){

   }

        if(result.getStatus()==ITestResult.SKIP){

        }

        if(result.getStatus()==ITestResult.FAILURE){

        }
    }

    @AfterClass
    public void afterClass()
    {

    }


    @AfterSuite
    public void afterSuite()
    {

    }
}
