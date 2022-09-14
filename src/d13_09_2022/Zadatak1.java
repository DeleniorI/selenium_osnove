package d13_09_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//div[contains(@class, 'oxd-form-actions orangehrm-login-action')]/button"))
				.click();

		Thread.sleep(5000);

		driver.findElement(By.xpath("//div[contains(@class, 'oxd-main-menu-search')]/input")).sendKeys("Me");

		driver.findElement(By.xpath("//ul[contains(@class, 'oxd-main-menu')]/li[1]/a")).click();
		Thread.sleep(1000);

		driver.findElement(By.className("oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//ul[contains(@class, 'oxd-dropdown-menu')]//a[text() = 'Logout']")).click();

		Thread.sleep(5000);
		driver.quit();

	}

}
