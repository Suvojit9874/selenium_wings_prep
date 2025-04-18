package table;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BootStrapTable_Orangehrm {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php");
		
		driver.manage().window().maximize();
		
		//Login steps
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		//Click on Admin
		driver.findElement(By.xpath("//a[contains(@href,'viewAdminModule')]")).click();
		
		
		//Table rows
		int rows=driver.findElements(By.xpath("//div[@class='oxd-table-body']/div")).size();
		
		
		//Reading data from the table

		//Xpath to capture value
		//div[@class='oxd-table-body']//div[@class="oxd-table-card"][1]//div//div[2]//div
		//Here div[@class="oxd-table-card"][1] is row number &  div[2] is column number
		
		System.out.println("Username"+"\t"+"User Role"+"\t"+"Employee Name"+"\t"+"Status");
		System.out.println("---------------------------------------------------------");
		
		for(int r=1;r<=rows;r++)
		{
			String username=driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']["+r+"]//div//div[2]//div")).getText();
			String userole=driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']["+r+"]//div//div[3]//div")).getText();
			String employeename=driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']["+r+"]//div//div[4]//div")).getText();
			String status=driver.findElement(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']["+r+"]//div//div[5]//div")).getText();
			
			System.out.println(username+"\t"+userole+"\t"+employeename+"\t"+status);
			
		}

		
		
	}

}






