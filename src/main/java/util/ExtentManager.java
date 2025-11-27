package util;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentManager implements ITestListener{
	ExtentSparkReporter spark;
	ExtentReports reports;
	ThreadLocal<ExtentTest> threadLocalTest=new ThreadLocal<>();
	public  void onStart(ITestContext context) {
//		System.out.println("Extent report is started");
		String date=new SimpleDateFormat("yyyyMMdddd HHmmss").format(new Date());
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+date+".html");
		spark.config().setDocumentTitle("ExtentReport");
		spark.config().setReportName("ListnersReport");
		spark.config().setTheme(Theme.STANDARD);
		
		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("browser", "chrome");
		reports.setSystemInfo("Env", "QA");
		reports.setSystemInfo("user", "Abhishek");
		
		
	}
	
	public void onTestStart(ITestResult result) {
		ExtentTest test=reports.createTest(result.getName());
		threadLocalTest.set(test);
		test.info("Testing Started.");
		
	}
	
	public void onTestSuccess(ITestResult result) {
		ExtentTest test=reports.createTest(result.getName());
		threadLocalTest.set(test);
		test.log(Status.PASS, "TestCase "+ result.getName()+" is passed");
		
	}
	public void onTestFailure(ITestResult result) {
	ExtentTest test=reports.createTest(result.getName());
	threadLocalTest.set(test);
	test.log(Status.FAIL, "TestCase "+result.getName()+" failed");
	WebDriver driver=(WebDriver)result.getTestContext().getAttribute("driver");
	try {
		test.addScreenCaptureFromPath(Screenshot.TakeScreenshot(driver, "Screenshot"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }
	
	public void onFinish(ITestContext context) {
	    reports.flush();
	  }

}

//
//
//package util;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//public class ExtentManager implements ITestListener{
//	ExtentSparkReporter spark;
//	ExtentReports reports;
//	ExtentTest test;
//	public  void onStart(ITestContext context) {
//		System.out.println("Extent report is started");
//		String date=new SimpleDateFormat("yyyyMMdddd HHmmss").format(new Date());
//		spark=new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+date+".html");
//		spark.config().setDocumentTitle("ExtentReport");
//		spark.config().setReportName("ListnersReport");
//		spark.config().setTheme(Theme.STANDARD);
//		
//		reports=new ExtentReports();
//		reports.attachReporter(spark);
//		reports.setSystemInfo("OS", "Windows");
//		reports.setSystemInfo("browser", "chrome");
//		reports.setSystemInfo("Env", "QA");
//		reports.setSystemInfo("user", "Abhishek");
//		
//		
//	}
//	
//	public void onTestStart(ITestResult result) {
//		test=reports.createTest(result.getName());
//		test.info("Testing started");
//		
//	}
//	
//	public void onTestSuccess(ITestResult result) {
//		test=reports.createTest(result.getName());
//		test.log(Status.PASS, "TestCase "+result.getName()+" is Passed");
//		
//	}
//	public void onTestFailure(ITestResult result) {
//		test=reports.createTest(result.getName());
//		test.log(Status.FAIL, "TestCase "+result.getName()+" is failed please see screenshot");
//		WebDriver driver=(WebDriver)result.getTestContext().getAttribute("driver");
//		try {
//			String path=Screenshot.TakeScreenshot(driver, "File1");
//			test.addScreenCaptureFromPath(path);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//we will take screenshot here after creating screenshot function.
//	}
//	public void onTestFailedWithTimeout(ITestResult result) {
//	    onTestFailure(result);
//	  }
//	
//	public void onFinish(ITestContext context) {
//	    reports.flush();
//	  }
//
//}
//
//
