package toqio.TestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import toqio.TestComponents.BaseTest;
import toqio.pageobjects.CartPage;
import toqio.pageobjects.CheckoutPage;
import toqio.pageobjects.ConfirmationPage;
import toqio.pageobjects.LandingPage;
import toqio.pageobjects.OrderPage;
import toqio.pageobjects.ProductCatalogue;

public class SubmitOrderTestHash extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test (dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		// page object model design
		ProductCatalogue productCatalogue = HomePage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDispaly(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		//ConfirmationPage ConfirmationPage = checkoutPage.submitOrder();
		//String ConfirmMessage = ConfirmationPage.getConfirmationMessage();
		//Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	@Test (dependsOnMethods= {"submitOrder()"})
	public void OrderHistoryTest() {
		//verify if ADIDAS is displaying in orders page
		ProductCatalogue productCatalogue = HomePage.loginApplication("testingwdg17@gmail.com", "Olivo2019");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDispaly(productName));
	}
	
	@DataProvider
	public Object [][] getData(){
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "testingwdg17@gmail.com");
		map.put("password", "Olivo2019");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "IPHONE 13 PRO");
		
		return new Object [][] {{map},{map1}};
	}
	
	//@DataProvider
	//public Object [][] getData(){
		
	//	return new Object [][] {{"testingwdg17@gmail.com","Olivo2019","ADIDAS ORIGINAL"},{"anshika@gmail.com","Iamking@000","IPHONE 13 PRO"}};
	//}

}
