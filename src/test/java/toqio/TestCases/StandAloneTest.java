package toqio.TestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import toqio.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ADIDAS ORIGINAL";

		WebDriverManager.chromedriver().setup(); 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage HomePage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("testingwdg17@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Olivo@2019");
		driver.findElement(By.id("login")).click();
		
		/// grab all the items into a list
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// iterate through all the products and check what you want
		// we use Java streams or for loop
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		// already filtered by above line
		// button:nth-child(4)")).click();
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// wait for the loading and success message
		// we use explicit wait for that section

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// class used by the spinner - ng-animating given by rahul
		 wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		// check if our product exists in the car
		// apply stream to check the products
		// returns a boolean
		Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equals(productName));
		Assert.assertTrue(match);

		// li[class='totalRow'] button[type='button']
		driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();

		// enter email
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')])[2]")).click();
		//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		//place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// Assert.assertEquals(ConfirmMessage, " Thankyou for the order. ");
		driver.close();
	}

}
