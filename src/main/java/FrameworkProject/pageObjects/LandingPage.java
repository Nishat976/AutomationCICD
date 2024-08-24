package FrameworkProject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameworkProject.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//Pagefactor
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	//login
	@FindBy(id="login")
	WebElement submitButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;

	
	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCart LoginApplication(String username,String password) {
		userName.sendKeys(username);
		userPassword.sendKeys(password);
		submitButton.click();
		ProductCart cart = new ProductCart(driver);
		return cart;
	}
	public String getErrormessage() {
		waitforWebElementstovisible(errormessage);
		return errormessage.getText();
	}
	
}
