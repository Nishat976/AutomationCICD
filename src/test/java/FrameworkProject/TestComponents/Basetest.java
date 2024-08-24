package FrameworkProject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameworkProject.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class Basetest {
	public WebDriver driver;
	public LandingPage landing;

	public WebDriver InitializeDriver() throws IOException {

		// Properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//FrameworkProject//Resources//Globaldata.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		// chromeBrowser
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		// firefoxBrowser
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	
		//String to HashMap Jackson DataBind
		ObjectMapper mapper = new ObjectMapper(); 
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });

		return data;
		
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage lauchApplication() throws IOException {
		driver = InitializeDriver();
		landing = new LandingPage(driver);
		landing.Goto();
		return landing;
	}
	@AfterMethod(alwaysRun=true)
	public void close() {
		driver.close();
	}
	public String TakeScreenshot(String testCasename,WebDriver driver) throws IOException {
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports" +testCasename +".png");
		FileUtils.copyFile(source, file); 
		return System.getProperty("user.dir")+"//reports"+testCasename +".png";
		
	}
	//Extent Reports

}
