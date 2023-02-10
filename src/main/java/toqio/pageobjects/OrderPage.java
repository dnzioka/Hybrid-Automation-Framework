package toqio.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import toqio.ReusableComponents.ReusableComponent;

public class OrderPage extends ReusableComponent {
	
	WebDriver driver; 
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List <WebElement> productTitles;
	
	@FindBy(css="tr td:nth-child(3)")
	private List <WebElement> orderNames;
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrderDispaly(String productName) {
		Boolean match = orderNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	


}
