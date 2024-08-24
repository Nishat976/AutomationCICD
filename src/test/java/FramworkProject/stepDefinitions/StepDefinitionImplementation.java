package FramworkProject.stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import FrameworkProject.TestComponents.Basetest;
import FrameworkProject.pageObjects.CartDetails;
import FrameworkProject.pageObjects.CheckOutPage;
import FrameworkProject.pageObjects.ConfirmationPage;
import FrameworkProject.pageObjects.LandingPage;
import FrameworkProject.pageObjects.ProductCart;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends Basetest {

	public LandingPage landing;
	public ProductCart cart;
	public ConfirmationPage confirm;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		
		landing = lauchApplication();
	}	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password) {
		cart = landing.LoginApplication(username,password);
	}
	@When("^I add the product (.+) to cart$")
	public void i_add_the_product_to_cart(String Productname) throws InterruptedException {
		
		cart.addtoCart(Productname);
	}
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String Productname) {
		CartDetails page = cart.cartlist();
		Boolean match = page.FilterList(Productname);
		Assert.assertTrue(match);
		CheckOutPage c = page.Checkout();
		c.CheckoutDetailsfilling("india");
		confirm = c.placeorder();
		
	}
	@Then("{string} message is displayed for the confirmation")
	public void message_is_displayed_for_the_confirmation(String string) {
		
		String message = confirm.getConfirmMessage();
		Assert.assertTrue(string.equalsIgnoreCase(message));
		driver.close();
	}
    @Then("^\"([^\"]*)\" message is displayed$")
    public void _message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1,landing.getErrormessage());
    	driver.close();
    }

	
	
	
}
