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

public class ProductCart extends AbstractComponents{
	WebDriver driver;
	
	public ProductCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//List<WebElement>
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	
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
	
	By ProductsBy = By.cssSelector(".mb-3");
	By addtoCart = By.cssSelector("button[class='btn w-10 rounded']");
	By toastmessage = By.id("toast-container");
	By loader = By.cssSelector(".ng-animating");
	By checkoutbutton = By.cssSelector(".totalRow button");
	By submitbutton = By.cssSelector("a[class*='btnn action__submit']");
	
	public List<WebElement> getList() {
		waitforElementstovisible(ProductsBy);
		return products;
	}
	
	public WebElement getProductName(String Productname) {
		WebElement p = getList().stream().filter
				(product->product.findElement(By.tagName("b")).getText()
				.equals(Productname)).findFirst().orElse(null);
		return p;
	}
	public void addtoCart(String ProductName) throws InterruptedException
	{
		WebElement name =getProductName(ProductName);
		name.findElement(addtoCart).click();
		waitforElementstovisible(toastmessage);
		waitforElementstoinvisible(loader);
	}
	
	
}
