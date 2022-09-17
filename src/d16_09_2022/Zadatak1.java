package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
		File front = new File("img/front_ilija_nestorovic.jpg");
		File back = new File("img/back_ilija_nestorovic.jpg");
		File left = new File("img/left_ilija_nestorovic.jpg");
		File right = new File("img/right_ilija_nestorovic.jpg");
		ArrayList<File> nizSlika = new ArrayList<>();
		Random r = new Random();
		nizSlika.add(front);
		nizSlika.add(left);
		nizSlika.add(back);
		nizSlika.add(right);

		driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");

		for (int i = 0; i < nizSlika.size(); i++) {
			driver.findElement(By.xpath("//img[contains(@alt, 'image " + (i + 1) + "' )]")).click();
			driver.findElement(By.xpath("//img[contains(@alt, '+ Add photo')]")).click();
			driver.findElement(By.id("imageUpload")).sendKeys(nizSlika.get(i).getAbsolutePath());
			wait.until(ExpectedConditions
					.numberOfElementsToBe(By.xpath("//div[contains(@class, 'sc-ftvSup fqhTDx')]//img"), i + 1));
			driver.findElement(By.xpath("//div[contains(@class, 'sc-gKXOVf bNhUVa')][1]//img")).click();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text() = 'Use One Side Only']")));
			driver.findElement(By.xpath("//button[text() = 'Use One Side Only']")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'reactEasyCrop_Container')]")));
			driver.findElement(By.xpath("//button[text() = 'Done']")).click();

		}
		List<WebElement> listaElemenata = driver.findElements(By.xpath("//div[contains(@class, 'sc-hZgfyJ')]/div"));
		int randomKonfeti = r.nextInt(listaElemenata.size());
		listaElemenata.get(randomKonfeti).click();
		driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();

		boolean sePojavila = true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@action = 'error']")));

		} catch (TimeoutException e) {
			sePojavila = false;
		}

		if (sePojavila) {
			System.out.println("Greska se pojavila");
		} else {
			System.out.println("Greska se nije pojavila");
		}

		Thread.sleep(5000);

		driver.quit();
	}

}
