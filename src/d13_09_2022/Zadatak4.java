package d13_09_2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Scanner s = new Scanner(System.in);
		List<Character> listaKaraktera = new ArrayList<>();
		driver.get("https://www.calculatorsoup.com/calculators/math/basic.php");
		Thread.sleep(500);

		System.out.print("Unesite formulu: ");
		String formula = s.next();

		for (char k : formula.toCharArray()) {

			listaKaraktera.add(k);
			Thread.sleep(500);

		}

		for (int i = 0; i < listaKaraktera.size(); i++) {
			driver.findElement(By.id("display")).sendKeys(listaKaraktera.get(i).toString());
			Thread.sleep(500);

		}

		driver.findElement(By.id("display")).sendKeys(Keys.ENTER);

		Thread.sleep(5000);
		driver.quit();

	}
}
