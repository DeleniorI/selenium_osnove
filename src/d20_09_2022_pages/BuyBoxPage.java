package d20_09_2022_pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyBoxPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public BuyBoxPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public WebElement getQuantityInput() {
		return driver.findElement(By.id("quantity_wanted"));

	}

	public Select getSize() {

		return new Select(driver.findElement(By.id("group_1")));
	}

	public WebElement getAddToCartButton() {
		return driver.findElement(By.name("Submit"));
	}

	public WebElement getAddToWishListButton() {
		return driver.findElement(By.id("wishlist_button"));
	}

	public WebElement getColors(String color) {
		return driver.findElement(By.xpath("//*[@id ='color_to_pick_list']/li/a[contains(@title, '" + color + "')]"));
	}
	
	public WebElement getProductPrice() {
		return driver.findElement(By.id("our_price_display"));
	}

	public void scrollToBuyBoxElement() {
		WebElement wishListButton = driver.findElement(By.id("wishlist_button"));
		Actions actions = new Actions(driver);
		actions.scrollToElement(wishListButton);
		actions.perform();
	

	}
}
