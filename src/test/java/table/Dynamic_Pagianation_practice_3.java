package table;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dynamic_Pagianation_practice_3 {

	// Gainers - Daily, Weekly, Monthly
	// Groups - Group A, Group B, Group M, Group T, Group MT, Group Z
	
	public static void changePercentBasedOnTime(WebDriver driver, int gainers, int groups) {

		for (int i = 1; i <= gainers; i++) {

			WebElement gainersByTime = driver.findElement(By.xpath("//h2[normalize-space()='Gainers']/following-sibling::div["+i+"]"));
			System.out.println("********* Based on "+gainersByTime.getText()+" ***********");

			gainersByTime.click();  // Daily/Weekly/Monthly
			
			for (int g = 1; g <= groups; g++) {

				WebElement activeGroup = driver.findElement(By.xpath("//div[@id='leftcontainer']//div[4]//a["+g+"]"));
				System.out.println("<<<<<Name of The Group is "+activeGroup.getText()+ ">>>>>");

				activeGroup.click();

				int indGroupRowNum = driver.findElements(By.xpath("//table[@class='dataTable']//tbody//tr")).size();

				String eachChange[] = new String[indGroupRowNum];

				for (int r = 1; r <= indGroupRowNum; r++) {

					String Change = driver.findElement(By.xpath("//table[@class='dataTable']//tbody//tr[" + r + "]//td[5]"))
							.getText();

					eachChange[r - 1] = Change;

				}

				Arrays.sort(eachChange);

				String lowestChange = eachChange[0];
			

				System.out.println("The lowest Change percent :" + lowestChange);

				for (int r = 1; r <= indGroupRowNum; r++) {

					String Change = driver.findElement(By.xpath("//table[@class='dataTable']//tbody//tr[" + r + "]//td[5]")).getText();

					if (Change.equals(lowestChange)) {

						String company = driver.findElement(By.xpath("//table[@class='dataTable']//tbody//tr[" + r + "]//td[1]")).getText();

						System.out.println("Company name And Percentage Change :" + company + "\t" + lowestChange);

					}

				}

			}

		}

	}
	
	
	// Gainers - Daily, Weekly, Monthly
	// Groups - All

	public static void allData(WebDriver driver, int gainers) {

		for (int i = 1; i <= gainers; i++) // Daily Weekly, Monthly
			{
				WebElement gainersByTime = driver.findElement(By.xpath("//h2[normalize-space()='Gainers']/following-sibling::div["+i+"]"));
				System.out.println("********* Based on "+gainersByTime.getText()+" ***********");
	
				gainersByTime.click();  // Daily/Weekly/Monthly
	
				WebElement group = driver.findElement(By.xpath("//strong[contains(text(),'All')]"));
				System.out.println("<<<<<Name of The Group is "+group.getText()+ ">>>>>");

				group.click();  //All

				int indGroupRowNum = driver.findElements(By.xpath("//table[@class='dataTable']//tbody//tr")).size();

				System.out.println("Number of rows are :" + indGroupRowNum);

				//Limited to 5 rows since data is very huge.
				for (int r = 1; r <= 5; r++) 	//for (int r = 1; r <= indGroupRowNum; r++)
					{

					String company = driver.findElement(By.xpath("//table[@class='dataTable']//tbody//tr["+ r + "]//td[1]")).getText();
					
					String Change = driver.findElement(By.xpath("//table[@class='dataTable']//tbody//tr[" + r +"]//td[5]")).getText();

					System.out.println(company + "\t" + Change);
				}

				System.out.println();

			}

		}


	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://money.rediff.com/gainers/bse/daily/groupall");

		driver.manage().window().maximize();

		// Type of Gainers (Daily, Weekly, Monthly)
		int gainers = driver.findElements(By.xpath("//h2[normalize-space()='Gainers']/following-sibling::div")).size();
		System.out.println("Gainer types :" + gainers);  //3

		// All group Rows
		int allGroupRows = driver.findElements(By.xpath("//table[@class='dataTable']//tbody/tr")).size();

		System.out.println("number of rows availble in All group are :" + allGroupRows);

		// Number of Columns
		int columns = driver.findElements(By.xpath("//table[@class='dataTable']//thead//th")).size();

		System.out.println("Number of available columns are :" + columns);  //5

		// Number of Groups (Pages)
		int groups = driver.findElements(By.xpath("//div[@id='leftcontainer']//div[4]//a")).size();

		System.out.println("Number of groups(Pages) are :" + groups);  //6

		
		//Test case 1:
		//1) Read All data on Daily, Weekly and Monthly wise (From Gainers)
		//2) Print Company, Group and %change. 
		
		allData(driver, gainers); //user defined method
		
		
		
		//Test case 2:
		//	1) Read data from all the groups on Daily, Weekly and Monthly wise (From Gainers)
		//	2) In each group print company name which is having lowest %Change 
		
		changePercentBasedOnTime(driver, gainers, groups); ///user defined method

	}

}