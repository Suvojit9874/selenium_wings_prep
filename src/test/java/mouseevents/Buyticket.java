package mouseevents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/* Test Case
 1) Open https://dummy-tickets.com/buyticket
 2) Click on Both
 3) Select round trip
 4) Under Flight,  select From, To, Departure, Return
 5) Under Hotel , select city, checkin, checkout
 6) Click on submit
  
  
 */



public class Buyticket 
{
	//UDF for selecting future date
	static void selectFutureDate(WebDriver driver,String year,String month,String date)
	{
		//select year
		while(true)
		{
			String currentYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();//actual year
					
			if(currentYear.equals(year))
				{
				  break;
				}
					
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();// Next
		}
		//select month
		WebElement month_drop=driver.findElement(By.xpath("//select[@aria-label='Select month']"));
		Select monthdrp=new Select(month_drop);
		monthdrp.selectByVisibleText(month);
		
		//date selection
		List<WebElement> allDates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tr//td//a"));
		
		  for(WebElement dt:allDates)
			 {
				if(dt.getText().equals(date))
				    {
						dt.click();
						break;
					}	
			 }
	}
	
	////UDF for selecting previous date 
	static void selectPreviousDate(WebDriver driver,String year,String month,String date)
	{
		//select year
		while(true)
		{
			String currentYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();//actual year
					
			if(currentYear.equals(year))
				{
						break;
				}
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();//Previous
		}
		//select month
		WebElement month_drop=driver.findElement(By.xpath("//select[@aria-label='Select month']"));
		Select monthdrp=new Select(month_drop);
		monthdrp.selectByVisibleText(month);
		
		//date selection
		List<WebElement> allDates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tr//td//a"));
		
		  for(WebElement dt:allDates)
			 {
				if(dt.getText().equals(date))
				    {
						dt.click();
						break;
					}	
			 }
	}

	public static void main(String[] args) throws Exception 
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://dummy-tickets.com/buyticket");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//a[normalize-space()='Both']")).click();
		driver.findElement(By.xpath("//input[@value='roundtripfh']")).click();
		
		//from
		driver.findElement(By.xpath("(//input[@name='source[]'])[5]")).sendKeys("Bhubaneswar ");
		Thread.sleep(3000);
		
		List<WebElement> from=driver.findElements(By.xpath("//div[@class='suggestion-block']//p"));	
				
		for(WebElement fm:from)
			{
				if(fm.getText().contains("Bhubaneswar Airport"))
				{
					fm.click();
					break;
				}
					
			}
			
		//to
		driver.findElement(By.xpath("(//input[@name='destination[]'])[5]")).sendKeys("Bangalore ");
		Thread.sleep(3000);
		List<WebElement> to=driver.findElements(By.xpath("(//ul[@class='suggestions-list'])//p"));	
		for(WebElement t:to)
		{
			if(t.getText().contains("HAL Bangalore International Airport"))
			{
				t.click();
				break;
			}	
		}
		//expected data - departure
		String year="2023";
		String month="Jul";
		String date="10";
		
		//open date pickers
		driver.findElement(By.xpath("(//input[@placeholder='Departure Date'])[5]")).click();
		selectPreviousDate(driver,year,month,date); //departure
		
		driver.findElement(By.xpath("(//input[@placeholder='Return Date'])[2]")).click();
		selectFutureDate(driver,"2026","Feb","15"); //return
		
		
		
		//city
		driver.findElement(By.xpath("(//input[@name='city[]'])[3]")).sendKeys("Bangalore");
		Thread.sleep(3000);
		
		List<WebElement> citys=driver.findElements(By.xpath("//ul[@class='suggestions-cities-list']//li//p"));	
		for(WebElement ct:citys)
		{
			if(ct.getText().equals("Bangalore"))
			{	
				ct.click();
				break;
			}
		}
		
		
		//check-in
		
		driver.findElement(By.xpath("(//input[@placeholder='Check In Date'])[3]")).click();
		selectPreviousDate(driver,"2024","Aug","10"); //departure

		
		//check-out
		driver.findElement(By.xpath("(//input[@placeholder='Check Out Date'])[3]")).click();
		selectFutureDate(driver,"2025","Jul","1");
		Thread.sleep(2000);
       
		//submit
		driver.findElement(By.xpath("//form[@id='both_round']//input[@value='Submit']")).click();
		Thread.sleep(3000);
		
		//validation
		String text=driver.findElement(By.xpath("//div[@class='before_title']")).getText();
		
		if(text.equals("Booking Details"))
		{
			System.out.println("Test Passed....");
		}
		else
		{
			System.out.println("Test Failed....");
		}
		
		driver.quit();
	}
}
