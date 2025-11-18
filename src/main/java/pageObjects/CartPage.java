package pageObjects;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.Assertion;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class CartPage extends BaseObject{
	//constructoe
	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	//locators
	@FindBy(xpath="//*[@id='toast-container']") WebElement toastPopup;
	@FindBy(xpath = "//ul//button/i[@class='fa fa-shopping-cart']") WebElement cartIcon;
	@FindBy(xpath="//div/ul//h3") List<WebElement> cartItems;
	@FindBy(xpath = "//button[normalize-space()='Checkout']") WebElement checkOutBtn;
	
	//action
	public void clickCart() {
		wait.until(ExpectedConditions.visibilityOf(toastPopup));
		wait.until(ExpectedConditions.invisibilityOf(toastPopup));
		cartIcon.click();
	}
	
	public String verifyCartItems(String product) {
		WebElement matchProduct=cartItems.stream().filter(item->item.getText().equalsIgnoreCase(product)).findFirst().orElse(null);
		System.out.println(matchProduct.getText());
		return matchProduct.getText();
		
	}
	public PaymentPage checkout() {
		checkOutBtn.click();
		return new PaymentPage(driver);
	}
	
}
