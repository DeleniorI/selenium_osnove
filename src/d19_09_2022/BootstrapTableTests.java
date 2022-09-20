package d19_09_2022;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//		1.Zadatak
//		Kreirati BootstrapTableTests klasu koja ima:
//		Base url: https://s.bootsnipp.com
//		Test #1: Edit Row
//		Podaci:
//		First Name: ime polaznika
//		Last Name: prezime polaznika
//		Middle Name: srednje ime polanzika
//		Koraci:
//		Ucitati stranu /iframe/K5yrx
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		Klik na Edit dugme prvog reda
//		Sacekati da dijalog za Editovanje bude vidljiv
//		Popuniti formu podacima. 
//		Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//		Klik na Update dugme
//		Sacekati da dijalog za Editovanje postane nevidljiv
//		Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//		Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//		Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//		Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//		
//		Test #2: Delete Row
//		Podaci:
//		First Name: ime polaznika
//		Last Name: prezime polaznika
//		Middle Name: srednje ime polanzika
//		Koraci:
//		Ucitati stranu /iframe/K5yrx
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		Klik na Delete dugme prvog reda
//		Sacekati da dijalog za brisanje bude vidljiv
//		Klik na Delete dugme iz dijaloga 
//		Sacekati da dijalog za Editovanje postane nevidljiv
//		Verifikovati da je broj redova u tabeli za jedan manji
//		Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//		
//		Test #3: Take a Screenshot
//		Koraci:
//		Ucitati stranu  /iframe/K5yrx
//		Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//		Kreirati screenshot stranice. Koristan link https://www.guru99.com/take-screenshot-selenium-webdriver.html
//		Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: src/paket_za_domaci/nazivslike.png

public class BootstrapTableTests {
	private WebDriver driver;
	private String baseUrl = "https://s.bootsnipp.com";
	private WebDriverWait wait;
	private ArrayList<String> imePrezimeSrednje = new ArrayList<>(); // Cisto da iskomplikujem malo, aka "mrsim muda"

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		imePrezimeSrednje.add("Ilija");
		imePrezimeSrednje.add("Nestorovic");
		imePrezimeSrednje.add("Milivoje");
	}

	@Test(priority = 10)
	public void editRow() {
		driver.get(baseUrl + "/iframe/K5yrx");
		String actualResult = driver.getTitle();
		String expectedResult = "Table with Edit and Update Data - Bootsnipp.com";
		Assert.assertEquals(actualResult, expectedResult, "Title should be " + expectedResult);
		driver.findElement(By.xpath("//*[@id = 'd1']/td[5]/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

		List<WebElement> inputList = driver.findElements(By.xpath("//div[contains(@class, 'modal-body')]/input"));
		for (int i = 0; i < inputList.size(); i++) {
			inputList.get(i).clear();
			inputList.get(i).sendKeys(imePrezimeSrednje.get(i));
		}

		driver.findElement(By.id("up")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));

		// Izvinjavam se, mora se napravim pametan
		List<WebElement> tableHeadList = driver
				.findElements(By.xpath("//table//th[position() > 1 and position() < 5]")); // Lista samo zbog poruke, da
																							// bih znao da l je fName,
																							// lName ili mName

		List<WebElement> expectedCellList = driver.findElements(By.xpath("//tr[@id = 'd1']/td[@id]")); // Lista celija
																										// koje
																										// proveravamo
		for (int i = 0; i < expectedCellList.size(); i++) {
			Assert.assertEquals(imePrezimeSrednje.get(i), expectedCellList.get(i).getText(),
					tableHeadList.get(i).getText() + " should be " + imePrezimeSrednje.get(i));
		}

	}

	@Test(priority = 20)
	public void deleteRow() {
		driver.get(baseUrl + "/iframe/K5yrx");
		String actualResult = driver.getTitle();
		String expectedResult = "Table with Edit and Update Data - Bootsnipp.com";
		Assert.assertEquals(actualResult, expectedResult, "Title should be " + expectedResult);

		driver.findElement(By.xpath("//*[@id ='d1']/td[6]/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete")));
		driver.findElement(By.id("del")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("delete")));

		Assert.assertFalse(driver.findElement(By.xpath("//table/tbody/tr")).isDisplayed(),
				"Table size should be reduced by one");

	}
	
	@Test(priority = 30)
	public void takeAScreenshot() throws IOException {
		driver.get(baseUrl + "/iframe/K5yrx");
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File source = scrShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("img/d19_09_2022/test.png"));
		
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}