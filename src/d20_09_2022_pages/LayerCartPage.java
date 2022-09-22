package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LayerCartPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public LayerCartPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public WebElement getContiueShoppingButton() {
		return driver.findElement(By.xpath("//span[contains(@class, 'continue')]/span"));

	}
	
	public WebElement getProceedToCheckoutButton() {
		return driver.findElement(By.xpath("//div[contains(@class, 'clearfix')]//div[contains(@class, 'button-container')]/a"));
	}

	public WebElement getProductAttribute() {
		return driver.findElement(By.id("layer_cart_product_attributes"));
	}

	public WebElement getQuantityNumber() {
		return driver.findElement(By.id("layer_cart_product_quantity"));
	}


	public WebElement getTotalPrice() {
		return driver.findElement(By.id("layer_cart_product_price"));
	}

	public void waitForElementVisibility() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
	}

	public void waitForElementInvisibility() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("layer_cart")));
	}
	
	
}
