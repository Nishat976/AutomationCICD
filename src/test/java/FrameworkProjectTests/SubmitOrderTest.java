package FrameworkProjectTests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameworkProject.TestComponents.Basetest;
import FrameworkProject.pageObjects.CartDetails;
import FrameworkProject.pageObjects.CheckOutPage;
import FrameworkProject.pageObjects.ConfirmationPage;
import FrameworkProject.pageObjects.LandingPage;
import FrameworkProject.pageObjects.OrdersPage;
import FrameworkProject.pageObjects.ProductCart;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends Basetest {
	String Productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void Submitorder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String confirmtext = "Thankyou for the order.";
		ProductCart cart = landing.LoginApplication(input.get("username"), input.get("Password"));
		cart.addtoCart(input.get("Productname"));
		CartDetails page = cart.cartlist();
		Boolean match = page.FilterList(input.get("Productname"));
		Assert.assertTrue(match);
		CheckOutPage c = page.Checkout();
		c.CheckoutDetailsfilling("india");
		ConfirmationPage confirm = c.placeorder();
		String message = confirm.getConfirmMessage();
		Assert.assertTrue(confirmtext.equalsIgnoreCase(message));

	}

	@Test(dependsOnMethods = { "Submitorder" })
	public void OrderPage() throws IOException {
		// TODO Auto-generated method stub

		ProductCart cart = landing.LoginApplication("nisha76@gmail.com", "Example@123");
		OrdersPage page = cart.GotoOrdersPage();
		Assert.assertTrue(page.FilterList(Productname));

	}
	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDatatoMap(
				System.getProperty("user.dir") + "//src\\test\\java\\FrameworkProject\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	/*
	 * @DataProvider public Object[][] getData1() {
	 * 
	 * HashMap<String,String> map = new HashMap<String,String>();
	 * map.put("username", "nisha76@gmail.com"); map.put("Password", "Example@123");
	 * map.put("Productname", "ZARA COAT 3"); HashMap<String,String> map2 = new
	 * HashMap<String,String>(); map2.put("username", "nisha76@gmail.com");
	 * map2.put("Password", "Example@123"); map2.put("Productname",
	 * "ADIDAS ORIGINAL");
	 * 
	 * 
	 * }
	 */

}
