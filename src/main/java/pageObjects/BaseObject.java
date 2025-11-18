package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseObject {

	WebDriverWait wait;
	Actions act;
	protected WebDriver driver;
public BaseObject(WebDriver driver) {
	this.driver=driver;
	wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	act=new Actions(driver);
	}
}
