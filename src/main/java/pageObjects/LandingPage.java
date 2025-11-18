package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


import util.ConfigLoader;

public class LandingPage extends BaseObject{
//	WebDriver driver; Transfer this to BaseObject
	//constructor
	public LandingPage(WebDriver driver) {
		super(driver);
//		this.driver=driver; Transfer this to baseOnject
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		
	}
	private String appURL=ConfigLoader.getProperty("appURL");
	//locators
	@FindBy(xpath="//*[@id='userEmail']") WebElement emailText;
	@FindBy(xpath = "//*[@id='userPassword']") WebElement passwordText;
	@FindBy(xpath = "//*[@id='login']") WebElement loginBtn;
	
	public void goTo() {
//		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.get(appURL);

	}
	//action
	public ProductPage loginPage(String email,String password) {
		emailText.sendKeys(email);
		passwordText.sendKeys(password);
		loginBtn.click();
		return new ProductPage(driver);
	}
	}
