package d13_09_2022;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak2 {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Scanner s = new Scanner(System.in);
		driver.manage().window().maximize();
		driver.get("https://s.bootsnipp.com/iframe/WaXlr");
		List<WebElement> listaZvezdica = driver.findElements(By.xpath("//div[contains(@class, 'form-group')]/button"));
		
		System.out.print("Unesite broj na koji kliknuti: ");
		int broj = s.nextInt();
		
		listaZvezdica.get(broj - 1).click();
		
		Thread.sleep(10000);
		driver.quit();
	}
}
