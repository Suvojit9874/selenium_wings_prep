package practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAutomationPractice {

	public static void selectDate1(WebDriver driver, String day, String month, String year) {
		ArrayList<String> arr = new ArrayList<String>(List.of("January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"));

		String monthInNum = "0";
		int indexOf = arr.indexOf(month);
		if (indexOf > 9) {
			monthInNum = indexOf + 1 + "";
		} else {
			monthInNum = "0" + (indexOf + 1);
		}
		driver.findElement(By.id("datepicker")).sendKeys(monthInNum + "/" + day + "/" + year);

	}

	public static void selectDate2(WebDriver driver, String day, String month, String year) throws InterruptedException {
		ArrayList<String> arr = new ArrayList<String>(List.of("January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"));
		driver.findElement(By.id("datepicker")).click();
		System.out.println("clicked");
		
		WebElement prev = driver.findElement(By.xpath("//a[@title='Prev']"));
		System.out.println("prev");
		
		WebElement next = driver.findElement(By.xpath("//a[@title='Next']"));
		System.out.println("next");
		
		WebElement monthText = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
		System.out.println("monthText");
		
		WebElement yearText = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']"));
		System.out.println("year");

		while (!(month.contains(monthText.getText()) && yearText.getText().equalsIgnoreCase(year))) {
			if (month.contains(monthText.getText()) && yearText.getText().equalsIgnoreCase(year)) {
				driver.findElement(By.xpath("//a[normalize-space()='21']")).click();
				System.out.println("clicked date");
			} else {
				if (Integer.parseInt(year) > Integer.parseInt(yearText.getText())) {
					next.click();
					Thread.sleep(Duration.ofSeconds(5));
				} else {
					prev.click();
					Thread.sleep(Duration.ofSeconds(5));

				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
//		practice https://testautomationpractice.blogspot.com/
		WebDriver driver = new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Suvojit Chatterjee");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("schatterjee@gmail.com");
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("2545345345");
		driver.findElement(By.xpath("//textarea[@id='textarea']")).sendKeys("2545345345");
		driver.findElement(By.xpath("//input[@id='male']")).click();

		List<WebElement> elements = driver
				.findElements(By.xpath("//*[@id=\"post-body-1307673142697428135\"]/div[4]/div/input"));
		for (WebElement el : elements) {
			String value = el.getAttribute("value");
			if (value.equals("sunday") || value.equals("monday")) {
				el.click();
			}
		}

		WebElement element = driver.findElement(By.xpath("//*[@id='country']"));
		Select se = new Select(element);
		se.selectByValue("india");

		WebElement webElement = driver.findElement(By.xpath("//*[@id='colors']"));
		Select select = new Select(webElement);
		select.selectByValue("red");
		select.selectByValue("blue");

		WebElement webElement2 = driver.findElement(By.xpath("//*[@id='animals']"));
		Select select2 = new Select(webElement2);
		select2.selectByValue("cat");

		String month = "November";
		String day = "20";
		String year = "2001";
		TestAutomationPractice.selectDate2(driver, day, month, year);
	}

}
