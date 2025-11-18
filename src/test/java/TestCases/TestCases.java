package TestCases;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePackage.BaseClass;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;
import pageObjects.ProductPage;

public class TestCases extends BaseClass{
	 ProductPage productPage;
	 CartPage cartPage;
	 PaymentPage paymentPage;
	

	
	@Test(groups = "smoke", dataProvider = "getData")
	public void loginTest(HashMap<String, Object> input) throws InterruptedException {
	    System.out.println(">>> loginTest started");
	    System.out.println("Email: " + input.get("email"));
	    try {
	    	LandingPage landingPage=new LandingPage(getDriver());
	        productPage = landingPage.loginPage((String) input.get("email"), (String) input.get("password"));
	    } catch (Exception e) {
	        System.out.println("Exception in loginTest: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups= {"regression"},dataProvider="getData")
	public void addProduct(HashMap<String,Object> input) {
		LandingPage landingPage=new LandingPage(getDriver());
		productPage=landingPage.loginPage((String)input.get("email"),(String)input.get("password"));
		List<String> products=(List<String>) input.get("product");
		for(String eachProduct:products) {
		cartPage= productPage.addProductToCart(eachProduct);
		productPage.toastPopupWait();

		}
		
	}

	
	


	
}
