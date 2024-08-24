package FrameworkProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import FrameworkProject.AbstractComponents.AbstractComponents;

public class CartDetails extends AbstractComponents{
	WebDriver driver;
	
	public CartDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//List<WebElement>
	
	@FindBy(css=".cartSection h3")
	List<WebElement> ProductNameFilter;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	@FindBy(css="button[class*='ta-item list-group-item']:nth-child(3)")
	WebElement submit;
	
	@FindBy(css="input[placeholder='Select Country")
	WebElement SelectCountry;
	
	@FindBy(css="a[class*='btnn action__submit']")
	WebElement placeorder;
	
	@FindBy(css=".hero-primary")
	WebElement confirmmessage;
	
	
	By loader = By.cssSelector(".ng-animating");
	By checkoutbutton = By.cssSelector(".totalRow button");
	By submitbutton = By.cssSelector("a[class*='btnn action__submit']");
	
	
	public boolean FilterList(String ProductName) {
		boolean flag = ProductNameFilter.stream().anyMatch(cartproduct ->cartproduct.getText()
				.equalsIgnoreCase(ProductName));
		
		return flag;
	}
	public  CheckOutPage Checkout() {
		Scrollpage();
		waitforElementstovisible(checkoutbutton);
		checkout.click();
		CheckOutPage c = new CheckOutPage(driver);
		return c;
	}
	
}
