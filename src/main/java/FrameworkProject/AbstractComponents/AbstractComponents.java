package FrameworkProject.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkProject.pageObjects.CartDetails;
import FrameworkProject.pageObjects.OrdersPage;

public class AbstractComponents {
	WebDriver driver;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartlist;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void waitforElementstovisible(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitforWebElementstovisible(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitforElementstoinvisible(By findBy) throws InterruptedException
	{
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
		 */
		Thread.sleep(5000);
	}
	public void Scrollpage() {
		JavascriptExecutor JE = (JavascriptExecutor)driver;
		JE.executeScript("window.scrollBy(0,1200)");
	}
	public void ScrollTop() {
		JavascriptExecutor JE = (JavascriptExecutor)driver;
		JE.executeScript("window.scrollTo(0,document.body.scrollTop)");
	}
	public CartDetails cartlist() {
		cartlist.click();
		CartDetails page = new CartDetails(driver);
		return page;
	}
	public OrdersPage GotoOrdersPage() {
		orders.click();
		OrdersPage order = new OrdersPage(driver);
		return order;
	}

}
