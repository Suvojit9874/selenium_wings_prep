package session01;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class Class01 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.workmate.team-nsk.site/");
		
		driver.manage().window().maximize();
		
		
		String title = driver.getTitle();
		System.out.println(title);

		// class02
		// locators ---> id name linkText partialLinkText TagName className CSS selector
		// XPATH
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.findElement(By.className("create-org")).click();
		driver.findElement(By.tagName("input")).sendKeys("suvojitchatterjee100@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Earth@9831");
		driver.findElement(By.tagName("button")).click();

		WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("img")));
		boolean isDisplayed = element1.isDisplayed();
		System.out.println("Is logo displayed ? " + isDisplayed);

		// create a Project
//		WebElement element2 = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(
//						By.xpath("/html/body/div/div[2]/div/div/section[2]/div/div[12]/div")));
//		element2.click();
//		
//		driver.findElement(By.id("demo-helper-text-misaligned-no-helper")).sendKeys("NoMad Nation");
//		driver.findElement(By.xpath("/html/body/div[2]/div[3]/p/div[2]/div/input")).sendKeys("@gmai");
//		driver.findElement(By.xpath("/html/body/div[2]/div[3]/ul/div[1]")).click();
//		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[1]")).click();

		// link text and partial link text
		Thread.sleep(5000);
		driver.findElement(By.linkText("My Task")).click();

		// findElement -> NoSuchElementException
		// findElements -> will not throw any exception ,returns empty list , size()=0;
		
		
		
	}
}
