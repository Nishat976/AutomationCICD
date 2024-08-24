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

public class OrdersPage extends AbstractComponents{
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//List<WebElement>
	
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> orderslist;

	
	
	public boolean FilterList(String ProductName) {
		boolean flag = orderslist.stream().anyMatch(cartproduct ->cartproduct.getText()
				.equalsIgnoreCase(ProductName));
		
		return flag;
	}
	
}
