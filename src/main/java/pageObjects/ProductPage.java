package pageObjects;

import java.awt.print.PageFormat;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BaseObject{
//	WebDriver driver;Transfer this to BaseObject
public  ProductPage(WebDriver driver) {
	//constructor
	super(driver);
//	this.driver=driver;Transfer this to BaseObject
	PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
	//ADIDAS ORIGINAL
}
//locators
@FindBy(xpath="//div[@class='row']//div[@class='card']") List<WebElement> productList;
@FindBy(xpath = ".//*[normalize-space()='Add To Cart']") WebElement addToCartBtn;
@FindBy(xpath="//*[@id='toast-container']") WebElement toastPopup;




//Action



public CartPage addProductToCart(String product) {
	wait.until(ExpectedConditions.visibilityOfAllElements(productList));
	Optional<WebElement> matchItem=productList.stream().filter(item->{
		return item.findElement(By.xpath(".//h5")).getText().equalsIgnoreCase(product);
	}).findFirst();
	matchItem.ifPresentOrElse(
		    item -> item.findElement(By.xpath(".//*[normalize-space()='Add To Cart']")).click(),
		    () -> {throw new RuntimeException("Product not found: " + product);}
		);
	return new CartPage(driver);

}
public void toastPopupWait() {
	wait.until(ExpectedConditions.visibilityOf(toastPopup));
	wait.until(ExpectedConditions.invisibilityOf(toastPopup));
}

}
