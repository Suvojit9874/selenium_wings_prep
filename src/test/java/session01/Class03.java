package session01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Class03 {
	public static void main(String[] args) {
		// class 03

		WebDriver driver = new ChromeDriver();

		driver.get("https://demo.nopcommerce.com/");

		driver.manage().window().maximize();

//		CSS  selector

//		tag id 				->	tag#id	
//		driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys("T-shirts"); // valid
//		driver.findElement(By.cssSelector("#small-searchterms")).sendKeys("T-shirts"); //valid

//		tag class			->	tag.class
//		driver.findElement(By.cssSelector(".search-box-text")).sendKeys("T-shirts");  //valid
//		driver.findElement(By.cssSelector("input.search-box-text")).sendKeys("T-shirts"); //valid

//		tag attribute		->	tag[attribute="value"]
//		driver.findElement(By.cssSelector("input[placeholder=\"Search store\"]")).sendKeys("T-shirts");  //valid
//		driver.findElement(By.cssSelector("[placeholder=\"Search store\"]")).sendKeys("T-shirts");  //valid

//		tag class attribute	->	tag.class[attribute="value"]
		driver.findElement(By.cssSelector("input.search-box-text[placeholder=\"Search store\"]")).sendKeys("T-shirts"); // valid

	}
}
