package d20_09_2022_tests;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import d20_09_2022_pages.BuyBoxPage;
import d20_09_2022_pages.HeaderPage;
import d20_09_2022_pages.LayerCartPage;
import d20_09_2022_pages.TopMenuPage;

public class AutomationPracticeTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "http://automationpractice.com/";
	private BuyBoxPage buyBoxPage;
	private TopMenuPage topMenuPage;
	private HeaderPage headerPage;
	private LayerCartPage layerCartPage;
	private Actions actions;
	private SoftAssert softAssert;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(baseUrl);
		buyBoxPage = new BuyBoxPage(driver, wait);
		topMenuPage = new TopMenuPage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
		layerCartPage = new LayerCartPage(driver, wait);
		actions = new Actions(driver);
		softAssert = new SoftAssert();

	}

	@Test(priority = 10)
	public void addingProductToTheCart() {
		driver.get(baseUrl + "/index.php?id_product=1&controller=product");
		buyBoxPage.scrollToBuyBoxElement();

		// Get input element, clear it's field and type '3'
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("3");

		// Get size element and select the option 'L'
		buyBoxPage.getSize().selectByVisibleText("L");

		// Get color element with parameter Blue and click on it
		buyBoxPage.getColors("Blue").click();

		// Get Add to Cart button and click on it
		buyBoxPage.getAddToCartButton().click();

		// Wait for Layer Cart Page visibility
		layerCartPage.waitForElementVisibility();

		// Verify that expected quantity equals actual quantity
		String expectedQuantity = "3";
		String actualQuantity = layerCartPage.getQuantityNumber().getText();
		Assert.assertEquals(actualQuantity, expectedQuantity, "Quantity should be 3");

		// Verify that expected size equals actual size
		String expectedSize = "L";
		String actualSize = layerCartPage.getProductAttribute().getText();
		Assert.assertTrue(actualSize.contains(expectedSize), "Size should be 'L'");

		/*
		 * Remove the dollar sign, convert String to Double, and verify if Total Price
		 * is the same as Product Price * 3
		 */
		String totalPriceText = layerCartPage.getTotalPrice().getText().replaceAll("[$]", "");
		double totalPrice = Double.parseDouble(totalPriceText);
		String productPriceText = buyBoxPage.getProductPrice().getText().replaceAll("[$]", "");
		double productPrice = Double.parseDouble(productPriceText);
		Assert.assertTrue(totalPrice == productPrice * 3, "Total price should be 3 times the Product price");

		// Get Continue Shopping button and click on it
		layerCartPage.getContiueShoppingButton().click();

		// Wait for Layer Cart Page to be invisible
		layerCartPage.waitForElementInvisibility();

		// Get input element, clear it's field and type '1'
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("1");

		// Get size element and select the option 'S'
		buyBoxPage.getSize().selectByVisibleText("S");

		// Get color element with parameter Orange and click on it
		buyBoxPage.getColors("Orange").click();

		// Get Add to Cart button and click on it
		buyBoxPage.getAddToCartButton().click();

		// Wait for Layer Cart Page visibility
		layerCartPage.waitForElementVisibility();

		// Get Proceed to Checkout button and click on it
		layerCartPage.getProceedToCheckoutButton().click();

		// Verify if title of the page is 'Order - My Store'
		String expectedTitle = "Order - My Store";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title should be 'Order - My Store'");

	}

	@Test(priority = 20)
	public void topMenuMouseOver() {
		actions.moveToElement(topMenuPage.getWomenLink()).perform();
		softAssert.assertTrue(topMenuPage.getWomenSubMenuLink().isDisplayed(), "Women submenu should be displayed");

		actions.moveToElement(topMenuPage.getDressesLink()).perform();
		softAssert.assertTrue(topMenuPage.getDressesSubMenuLink().isDisplayed(), "Dresses submenu should be displayed");

		actions.moveToElement(topMenuPage.getTshirtsLink()).perform();
		softAssert.assertFalse(
				topMenuPage.getDressesSubMenuLink().isDisplayed() && topMenuPage.getWomenSubMenuLink().isDisplayed(),
				"Both submenus should NOT be displayed");

	}

	@Test(priority = 30)
	public void phoneNumberVisibilityCheckOnResize() {
		driver.manage().window().maximize();
		softAssert.assertTrue(headerPage.getShopPhoneNumber().isDisplayed(), "Phone number should be displayed");

		driver.manage().window().setSize(new Dimension(680, 636));
		softAssert.assertFalse(headerPage.getShopPhoneNumber().isDisplayed(), "Phone number should NOT be displayed");

		driver.manage().window().setSize(new Dimension(768, 700));
		softAssert.assertTrue(headerPage.getShopPhoneNumber().isDisplayed(), "Phone number should be displayed");
		driver.manage().window().maximize();
	}

	@Test(priority = 40)
	public void HeaderLinksCheck() {

		headerPage.getContactUsLink().click();
		String expectedContactTitle = "Contact us - My Store";
		String actualContactTitle = driver.getTitle();
		softAssert.assertEquals(actualContactTitle, expectedContactTitle, "Title should be 'Contact us - My Store'");

		headerPage.getSignInLink().click();
		String expectedLoginTitle = "Login - My Store";
		String actualLoginTitle = driver.getTitle();
		softAssert.assertEquals(actualLoginTitle, expectedLoginTitle, "Title should be 'Login - My Store'");

	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod
	public void afterMethod() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
