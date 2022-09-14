package d13_09_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak3 {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");

		for (int i = 0; i < 5; i++) {
			driver.findElement(By.xpath("//button[contains(@class, 'btn btn-info add-new')]")).click();
			driver.findElement(By.name("name")).sendKeys("Ilija Nestorovic");
			driver.findElement(By.name("department")).sendKeys("Quality Assurance Department");
			driver.findElement(By.name("phone")).sendKeys("(062)260-736");
			driver.findElement(
					By.xpath("//table[contains(@class, 'table table-bordered')]/tbody/tr[last()]/td[last()]/a[1]"))
					.click();
			;
			Thread.sleep(500);
		}
		Thread.sleep(5000);
		driver.quit();

	}

}
