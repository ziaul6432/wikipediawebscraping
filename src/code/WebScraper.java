package code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebScraper {

	public static void main(String args[]) {
		// https://chromedriver.chromium.org/downloads
		System.setProperty("webdriver.chrome.driver", "binary/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40)); // for wait before throwing exception
		driver.manage().window().maximize(); // operation for windows
		driver.get("https://www.wikipedia.org/");

		driver.findElement(By.xpath("//strong[contains(text(),'English')]")).click(); // xml path, linktext, css, name ,
																						// tagname
		driver.findElement(By.linkText("All portals")).click();
		driver.findElement(By.linkText("A–Z index")).click();
		driver.findElement(By.linkText("Z")).click();
		driver.findElement(By.linkText("Z")).click();
		driver.findElement(By.xpath(".//a/span[.='Name and pronunciation']")).click();
		// write in a file
		String h1 = driver.findElement(By.id("Name_and_pronunciation")).getText();
		List<WebElement> indexOne = driver
				.findElements(By.xpath(".//*[@id='Name_and_pronunciation']/parent::h2/following-sibling::p"));
		
		String content_1 = h1 + "\n";
		for (int i = 0; i < 3; i++) {
			content_1 = content_1 + indexOne.get(i).getText();
		}

		driver.findElement(By.xpath(".//a/span[.='History']")).click();

		String h3 = driver.findElement(By.id("History")).getText();

		List<WebElement> indexTwo = driver
				.findElements(By.xpath(".//*[@id='History']/parent::h2/following-sibling::*"));

		String content_2 = content_1 + "\n"+h3 + "\n";

		for (int i = 0; i < 18; i++) {
			content_2 = content_2 + indexTwo.get(i).getText();
		}

		driver.findElement(By.xpath(".//a/span[.='Variant and derived forms']")).click();

		String h4 = driver.findElement(By.id("Variant_and_derived_forms")).getText();

		List<WebElement> indexThree = driver
				.findElements(By.xpath(".//*[@id='Variant_and_derived_forms']/parent::h2/following-sibling::*"));

		String content_3 = content_2 +"\n"+ h4 + "\n";

		for (int i = 0; i < 4; i++) {
			content_3 = content_3 + indexThree.get(i).getText();
		}
		writeInAFile(content_3);
		driver.quit();
	}

	public static void writeInAFile(String content) {
		try {
			File myObj = new File("filename.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter("filename.txt");
			myWriter.write(content);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}
