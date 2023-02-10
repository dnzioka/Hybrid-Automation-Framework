package toqio.TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import toqio.TestComponents.BaseTest;
import toqio.TestComponents.IRetry;
import toqio.pageobjects.CartPage;
import toqio.pageobjects.CheckoutPage;
import toqio.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test (groups= {"ErrorHandling"},retryAnalyzer=IRetry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		// page object model design
		HomePage.loginApplication("shetty@gmail.com", "IamKing@00");
		Assert.assertEquals("Incorrect email  password.", HomePage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		// page object model design
		ProductCatalogue productCatalogue = HomePage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDispaly("ADIDAS ORIGINALs");
		Assert.assertFalse(match);
	}

}
