package toqio.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import toqio.ReusableComponents.ReusableComponent;

public class LandingPage extends ReusableComponent {

	WebDriver driver; //this is a local driver not aware of webdriver driver

	// create a constructor -> takes same name as class name but its the first 
	//method executes when the class is run
	public LandingPage(WebDriver driver) {
		
		super (driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmails = driver.findElement(By.cssSelector("#userEmail"));
	
	//pageFactory -> reduces syntax of creating WebElement like above
	@FindBy(css="#userEmail")
	WebElement useremail;
	
	@FindBy(css="#userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	
	public ProductCatalogue loginApplication(String email, String password) {
		useremail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	

}
