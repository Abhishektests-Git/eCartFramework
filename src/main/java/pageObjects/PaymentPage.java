package pageObjects;



import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PaymentPage extends BaseObject{
	//constructor
public PaymentPage(WebDriver driver) {
	super(driver);
	PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
}
//locators
@FindBy(xpath="//*[@class='payment__types']") WebElement paymentPage;
@FindBy(xpath = "//div[@class='payment__type payment__type--cc active']") WebElement creditCard;
@FindBy(xpath="//input[@class='input txt text-validated'][@type='text']") WebElement cardNumField;
@FindBy(xpath="(//select[@class='input ddl'])[1]") WebElement cardMonthDropD;
@FindBy(xpath="(//select[@class='input ddl'])[2]") WebElement cardYearDropD;
@FindBy(xpath="//div[@class='field small']//input[@class='input txt']") WebElement CVVField;
@FindBy(xpath="//div[@class='field']//input[@class='input txt']") WebElement nameField;
@FindBy(xpath="//*[@class='input txt text-validated ng-untouched ng-pristine ng-valid']") WebElement emailField;
@FindBy(xpath="//*[@placeholder='Select Country']") WebElement countryDropD;
@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']") WebElement countryListWait;
@FindBy(xpath="//span[@class='ng-star-inserted']") List<WebElement> countryList;
@FindBy(xpath="(//*[normalize-space()='Place Order'])[2]") WebElement placeOrder;
//action
public void selectCreditCard(){
wait.until(ExpectedConditions.visibilityOf(paymentPage));
creditCard.click();
}
public void paymentDetails(String cardNumber,String monthNum,String yearNum,String CVV,String name,String email,String country) {
	act.click(cardNumField).keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();
	cardNumField.sendKeys(cardNumber);
	Select selectMonth=new Select(cardMonthDropD);
	selectMonth.selectByVisibleText(monthNum);
	Select selectYear=new Select(cardYearDropD);
	selectYear.selectByVisibleText(yearNum);
	CVVField.sendKeys(CVV);
	nameField.sendKeys(name);
	emailField.sendKeys(email);
	countryDropD.sendKeys(country);
	wait.until(ExpectedConditions.visibilityOf(countryListWait));
	WebElement matchCountry=countryList.stream().filter(eachCountry->{
		return eachCountry.getText().equalsIgnoreCase(country);}).findFirst().orElse(null);
	matchCountry.click();	
}
public void placeOrder() {
	placeOrder.click();
}
}
