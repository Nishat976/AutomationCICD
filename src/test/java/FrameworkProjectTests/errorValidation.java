package FrameworkProjectTests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import FrameworkProject.TestComponents.Basetest;
import FrameworkProject.TestComponents.Retry;
import FrameworkProject.pageObjects.CartDetails;
import FrameworkProject.pageObjects.CheckOutPage;
import FrameworkProject.pageObjects.ConfirmationPage;
import FrameworkProject.pageObjects.LandingPage;
import FrameworkProject.pageObjects.ProductCart;
import io.github.bonigarcia.wdm.WebDriverManager;

public class errorValidation extends Basetest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginValidation() throws IOException {
		// TODO Auto-generated method stub
		String Productname ="ZARA COAT 3";
		String confirmtext ="Thankyou for the order.";
		//LandingPage landing = lauchApplication();
		ProductCart cart = landing.LoginApplication("nisha76@gmail.com", "Example@g123");
		Assert.assertEquals("Incorrect email or password.",landing.getErrormessage());
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String Productname ="ZARA COAT 3";
		String confirmtext ="Thankyou for the order.";
		//LandingPage landing = lauchApplication();
		ProductCart cart = landing.LoginApplication("nisha76@gmail.com", "Example@123");
		cart.addtoCart(Productname);
		CartDetails page = cart.cartlist();
		Boolean match = page.FilterList("ZARA COAT 32");
		Assert.assertFalse(match);
		
	}

}
