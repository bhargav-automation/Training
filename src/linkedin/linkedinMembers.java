package linkedin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class linkedinMembers {

	static WebDriver driver = null;
	static String str[] = null;
	static int CorrectResult;
	static int connections = 1;
	static int Outerloop = 1;
	static XSSFSheet sheet;
	static XSSFWorkbook workbook;
	static Row row;
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		// TODO Auto-generated method stub
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		Thread.sleep(500);
		driver.get("https://www.linkedin.com/");

		xpathExperssions xpath = new xpathExperssions();

		waitForElement(xpath.beforeSigin);
		driver.findElement(By.xpath(xpath.beforeSigin)).click();
		Thread.sleep(1000);
		waitForElement(xpath.userName);
		driver.findElement(By.xpath(xpath.userName)).sendKeys("jyothsna.cool14@gmail.com");
		waitForElement(xpath.password);
		driver.findElement(By.xpath(xpath.password)).sendKeys("143J1A0514");
		// sigin button
		waitForElement(xpath.Sigin);
		driver.findElement(By.xpath(xpath.Sigin)).click();

		waitForElement(xpath.Search);
		driver.findElement(By.xpath(xpath.Search)).sendKeys("\"Java developer\"+\"8 years experience\"");

		Thread.sleep(500);
		waitForElement(xpath.Searchbtn);
		driver.findElement(By.xpath(xpath.Searchbtn)).click();

		waitForElement(xpath.locationBtn);
		driver.findElement(By.xpath(xpath.locationBtn)).click();

		waitForElement(xpath.addCountry);
		WebElement addCountry = driver.findElement(By.xpath(xpath.addCountry));
		addCountry.sendKeys("France");
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_DOWN);
		rbt.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(500);
		rbt.keyPress(KeyEvent.VK_ENTER);
		rbt.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(2000);
		waitForElement(xpath.ApplyBtn);
		driver.findElement(By.xpath(xpath.ApplyBtn)).click();

		Thread.sleep(2000);
		
		String path = "E:\\Linkedin_data.xlsx";
		FileInputStream fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		row = sheet.createRow(0);
		Cell staticcell = row.createCell(0);
		staticcell.setCellValue("MemberName");
		Cell staticcell1 = row.createCell(1);
		staticcell1.setCellValue("MemberDesignation");
		Cell staticcell2 = row.createCell(2);
		staticcell2.setCellValue("MemberLocation");
		Cell staticcell3 = row.createCell(3);
		staticcell3.setCellValue("ProfileLink");

		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		try {
		waitForElement(xpath.Pagination);
		
			List<WebElement> elepageBtn = driver.findElements(By.xpath(xpath.Pagination));

			List<String> string = new ArrayList<String>();
			for (WebElement e : elepageBtn) {
				string.add(e.getText());
			}
			Outerloop = string.size();
			System.out.println("Outerloop " + Outerloop);
			Memberdetails(xpath.ListofMembers,xpath.NextBtn);
		}
		catch(Exception e){
			System.out.println("Catch lo vunnavu");
			Memberdetails(xpath.ListofMembers,xpath.NextBtn);
		}
		
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}

	public static void Memberdetails(String listofMembers, String nextBtn) throws InterruptedException
	{
		Thread.sleep(2000);
		for (int j = 1; j <= Outerloop; j++) {
			waitForElement(listofMembers);
			List<WebElement> eleliBtn = driver.findElements(By.xpath(listofMembers));

			List<String> strings = new ArrayList<String>();
			for (WebElement e : eleliBtn) {
				strings.add(e.getText());
			}
			int Innerloop = strings.size();
			System.out.println("Count " + Innerloop);

			for (int i = 1; i <= Innerloop; i++) {
				 row = sheet.createRow(connections);

				System.out.println("..............Member" + connections + "details.............");
				waitForElement("//ul[contains(@class,'search-result')]/li["+i+"]//div[2]//h3//span[contains(@class,'actor')]");
				String MemberName = driver
						.findElement(By.xpath(
								"//ul[contains(@class,'search-result')]/li["+i+"]//div[2]//h3//span[contains(@class,'actor')]"))
						.getText();
				System.out.println("MemberName " + MemberName);
				Cell cell = row.createCell(0);
				cell.setCellValue(MemberName);

				waitForElement("//ul[contains(@class,'search-result')]/li[" + i + "]//div[2]/p[1]");
				String MemberDesignation = driver
						.findElement(By.xpath("//ul[contains(@class,'search-result')]/li[" + i + "]//div[2]/p[1]"))
						.getText();
				System.out.println("MemberDesignation " + MemberDesignation);
				Cell cell2 = row.createCell(1);
				cell2.setCellValue(MemberDesignation);

				waitForElement("//ul[contains(@class,'search-result')]/li[" + i + "]//div[2]/p[2]");
				String MemberLocation = driver
						.findElement(By.xpath("//ul[contains(@class,'search-result')]/li[" + i + "]//div[2]/p[2]"))
						.getText();
				System.out.println("MemberLocation " + MemberLocation);
				Cell cell3 = row.createCell(2);
				cell3.setCellValue(MemberLocation);

				waitForElement("//ul[contains(@class,'search-result')]/li[" + i + "]//div[2]/a");
				WebElement MemberProfile = driver
						.findElement(By.xpath("//ul[contains(@class,'search-result')]/li[" + i + "]//div[2]/a"));
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
				MemberProfile.sendKeys(selectLinkOpeninNewTab);
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(750);
				String ProfileLink = driver.getCurrentUrl();
				System.out.println("ProfileLink " + ProfileLink);
				Cell cell4 = row.createCell(3);
				cell4.setCellValue(ProfileLink);
				driver.close();

				Thread.sleep(750);
				driver.switchTo().window(tabs.get(0));
				connections++;
			}

				if (j < Outerloop) {
				Thread.sleep(2000);
				WebElement CheckingElement = driver.findElement(By.xpath(nextBtn));
				CheckingElement.click();
				Thread.sleep(1500);
			}
			
		}
	}
	public static String waitForElement(String item) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(item)));
		return item;
	}
}
