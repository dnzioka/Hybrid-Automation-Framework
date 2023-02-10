package toqio.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import toqio.ReusableComponents.ReusableComponent;

public class ConfirmationPage extends ReusableComponent {
	WebDriver driver;
	
	@FindBy (css =".hero-primary")
	WebElement ConfirmationMessage;
	

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getConfirmationMessage() {
		
		return ConfirmationMessage.getText();
	}
	
	

}
