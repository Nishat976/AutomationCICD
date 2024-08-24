package FrameworkProjectTests;

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

import FrameworkProject.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String Productname ="ZARA COAT 3";
		String confirmtext ="Thankyou for the order.";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor JE = (JavascriptExecutor)driver;
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		LandingPage landing = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("nisha76@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Example@123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		WebElement p = products.stream().filter
				(product->product.findElement(By.tagName("b")).getText()
				.equals(Productname)).findFirst().orElse(null);
		p.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> filterlist =driver.findElements(By.cssSelector(".cartSection h3"));
		boolean flag = filterlist.stream().anyMatch(cartproduct ->cartproduct.getText().equalsIgnoreCase(Productname));
		Assert.assertTrue(flag);
		JE.executeScript("window.scrollBy(0,1200)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		driver.findElement(By.cssSelector("button[class*='ta-item list-group-item']:nth-child(3)")).click();
		JE.executeScript("window.scrollBy(0,1200)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class*='btnn action__submit']")));
		driver.findElement(By.cssSelector("a[class*='btnn action__submit']")).click();
		JE.executeScript("window.scrollTo(0,document.body.scrollTop)");
		String message =driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(message);
		Assert.assertTrue(confirmtext.equalsIgnoreCase(message));
		
	}

}
