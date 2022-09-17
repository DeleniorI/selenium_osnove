package d15_09_2022;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Zadatak3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://seeds.sproutsocial.com/components/loader-button/");
		WebElement paragraph = driver
				.findElement(By.xpath("//div[contains(@class, 'CodeSandbox__Preview-sc-9lhwa5-1 bcxDKy')]"));
		new Actions(driver).moveToElement(paragraph).perform();

		driver.findElement(By.xpath("//button[@data-qa-button-isloading = 'false']")).click();
		System.out.println("Loading started.");
		driver.findElement(By.xpath("//button[@data-qa-button-isloading = 'true']"));
		System.out.println("Loading in progress...");
		driver.findElement(By.xpath("//button[@data-qa-button-isloading = 'false']"));
		System.out.println("Loading finished.");
		
		Thread.sleep(5000);
		driver.quit();

	}

}
