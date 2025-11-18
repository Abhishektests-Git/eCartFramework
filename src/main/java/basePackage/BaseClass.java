package basePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;
import util.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BaseClass {

    // ✅ Thread-safe WebDriver
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
//    private String browser=configLoader.getProperty("browser");
//     private String appURL=configLoader.getProperty("appURL");
//    LandingPage landingPage;

    // ✅ Setup driver per thread
    public void setupDriver() {
    	 WebDriver localDriver;
    	 String browser =ConfigLoader.getProperty("browser");
    	 if(browser.equalsIgnoreCase("chrome")) {
        localDriver = new ChromeDriver();
    	 }
    	 else throw new IllegalStateException("browserName is not correct");
        threadDriver.set(localDriver);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
    }

    // ✅ Accessor for thread-local driver
    public WebDriver getDriver() {
        return threadDriver.get();
    }

    // ✅ Setup before each test method
    @BeforeMethod(alwaysRun = true)
    public void startApp(ITestContext context) {
    	ConfigLoader.loadConfig();
        setupDriver();
        context.setAttribute("driver", getDriver());
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.goTo();
    }

    // ✅ Teardown after each test method
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            threadDriver.remove(); // ✅ clean up thread-local
        }
    }

    // ✅ JSON data reader
    public List<HashMap<String, Object>> getJsonData(String filePath)
            throws StreamReadException, DatabindException, IOException {
        File file = new File(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<HashMap<String, Object>>>() {});
    }

    // ✅ DataProvider for test data
    @DataProvider(parallel = true) // ✅ enable parallel data-driven tests
    public Object[][] getData() throws StreamReadException, DatabindException, IOException {
        System.out.println("Reading data from JSON");
        List<HashMap<String, Object>> data = getJsonData(System.getProperty("user.dir")
                + File.separator + "TestData" + File.separator + "PurchaseOrder.json");

        Object[][] obj = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            obj[i][0] = data.get(i);
        }
        return obj;
    }
}


//package TestCases;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.List;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DatabindException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import PageObject.CartPage;
//import PageObject.LandingPage;
//import PageObject.PaymentPage;
//import PageObject.ProductPage;
//
//import org.openqa.selenium.support.ui.Select;
//public class BaseClass {
//	public static ThreadLocal<WebDriver> threadDriver=new ThreadLocal<WebDriver>();
//	 LandingPage landingPage;
//
//	//this will run by startApp fn
//public WebDriver setupDriver() throws InterruptedException {
//	WebDriver localDriver=new ChromeDriver();
//	threadDriver.set(localDriver);
//	return localDriver;
//	
//}
//
//public WebDriver getDriver() {
//	return threadDriver.get();
//}
//
//
//
//
////thread.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////driver.manage().window().maximize();
////WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
////return driver;
//
////@BeforeMethod
////public void startApp() throws InterruptedException {
////	driver=setupDriver();
////	landingPage=new LandingPage(driver);
////	landingPage.goTo();
////	
////}
//
//@BeforeMethod(alwaysRun = true)
//public void startApp() throws InterruptedException {
////	System.out.println(">>> TestCases startApp() executed");
//     // this method is inherited from BaseClass
//	setupDriver();
//	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	getDriver().manage().window().maximize();
//	WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(15));
//    landingPage = new LandingPage(getDriver());
//    System.out.println("Opening landing page");
//    landingPage.goTo();
////    return landingPage;//not necessary
//}
//@AfterMethod(alwaysRun = true)
//public void tearDown() throws InterruptedException {
//	getDriver().quit();
//}
//
//
//
////reading product data from json file
//public List<HashMap<String,Object>> getJsonData(String filePath) throws StreamReadException, DatabindException, IOException{
//	File file=new File(filePath);
//	ObjectMapper objectMapper=new ObjectMapper();
//	List<HashMap<String,Object>> data=objectMapper.readValue(file, new TypeReference<List<HashMap<String,Object>>>() {
//	});
//	return data;
//}
//
//@DataProvider
//public Object[][] getData() throws StreamReadException, DatabindException, IOException{
//	System.out.println("Reading data from JSON");
//	List<HashMap<String,Object>> data=getJsonData(System.getProperty("user.dir")+File.separator+"TestData"+File.separator+"PurchaseOrder2.json");
//	Object[][] obj=new Object[data.size()][1];
//	for(int i=0;i<data.size();i++) {
//		obj[i][0]=data.get(i);
//	}
//	return obj;
////	return new Object[][] {{data.get(0)},{data.get(1)}};
//}
//}