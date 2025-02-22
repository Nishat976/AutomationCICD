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

public class ConfirmationPage extends AbstractComponents{
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//List<WebElement>
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmmessage;
	
	
	
	public String getConfirmMessage() {
		String message =confirmmessage.getText();
		return message;
	}
}
