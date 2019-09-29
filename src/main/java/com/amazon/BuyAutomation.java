package com.amazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BuyAutomation {

	WebDriver webdrive1=null;
	
	public void chrome() throws IOException {
		FileInputStream fis=new FileInputStream("Res/config.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String val=(String) prop.getProperty("chrome");
		System.setProperty("webdriver.chrome.driver",val);
		
		webdrive1= new ChromeDriver();
		
	}
	
	
	public void edge() throws IOException {
		FileInputStream fis=new FileInputStream("Res/config.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String val=(String) prop.getProperty("edge");
		System.setProperty("webdriver.edge.driver", val);
		
		webdrive1=new EdgeDriver();
	}
	
	@Test(priority=1)
	@Parameters({"browser"})
	public void login(String browser) throws InterruptedException, IOException {
		if(browser.equals("chrome")) {
			chrome();
		}
		else if(browser.equals("edge")) {
			edge();
		}
		System.out.println(webdrive1);
		if(webdrive1!=null) {
			try {
				FileInputStream fis=new FileInputStream("Res/config.properties");
				Properties prop=new Properties();
				prop.load(fis);
				webdrive1.manage().window().maximize();
				
				webdrive1.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				webdrive1.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				
				WebDriverWait wait=new WebDriverWait(webdrive1, 10);
				Thread.sleep(4000);
				
				webdrive1.get(prop.getProperty("login"));

				WebElement elem=wait.until(ExpectedConditions.visibilityOf(webdrive1.findElement(By.name("email"))));
				elem.sendKeys(prop.getProperty("username")+Keys.RETURN);
				webdrive1.findElement(By.name("password")).sendKeys(prop.getProperty("password")+Keys.RETURN);
				Thread.sleep(15000);
				Assert.assertEquals(webdrive1.getCurrentUrl(), "https://www.amazon.in/?ref_=nav_ya_signin&");
				
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Test(priority = 2, dependsOnMethods = {"login"})
	public void addToCart() throws InterruptedException, IOException {
		FileInputStream fis=new FileInputStream("Res/config.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String val=(String) prop.getProperty("url");

		WebDriverWait wait=new WebDriverWait(webdrive1, 10);
		WebElement elem;
		Actions actions=new Actions(webdrive1);
		
		FileInputStream fis1=new FileInputStream("Res/Book1.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis1);
		XSSFSheet sheet=workbook.getSheet("data");
		
		
		for(int i=0;i<10;i++) {
			XSSFRow row=sheet.getRow(i);
			Screen screen=new Screen();
			Pattern search=new Pattern(System.getProperty("user.dir")+"/Res/Screenshot (79).png");
			try {
				screen.type(search, row.getCell(0).getStringCellValue()+Keys.RETURN);

			} catch (FindFailed e) {
				e.printStackTrace();
			}
			webdrive1.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div")).click();
			webdrive1.findElement(By.xpath("//img[@alt='"+row.getCell(1).getStringCellValue()+"'][1]")).click();
			
			ArrayList<String> tabs2 = new ArrayList<String> (webdrive1.getWindowHandles());
			System.out.println(tabs2);
			webdrive1.close();
			webdrive1.switchTo().window(tabs2.get(1));
			screenShot("book"+i);
			try {
				webdrive1.findElement(By.xpath("//button[@aria-label=\"No, thanks\"]")).click();
				
				elem=wait.until(ExpectedConditions.elementToBeClickable(By.id("soldByThirdParty")));
				do {
					Action build=actions.moveToElement(elem).build();
					build.perform();
					
					elem.click();
					System.out.println(webdrive1.findElement(By.xpath("//input[@id='buy-now-button']")).isDisplayed());
				}while(!webdrive1.findElement(By.xpath("//input[@id='buy-now-button']")).isDisplayed());
			}catch(Exception e) {
				System.out.println("Error"+e.getMessage());
			}
			
			System.out.println(webdrive1.getTitle());
			
			
			webdrive1.findElement(By.xpath("//input[@id='buy-now-button']")).click();
			DataFormatter formatter = new DataFormatter(); 
			Cell cell;
			
			webdrive1.findElement(By.name("enterAddressFullName")).sendKeys(row.getCell(2).getStringCellValue());
			cell = row.getCell(3);
			webdrive1.findElement(By.name("enterAddressPhoneNumber")).sendKeys(formatter.formatCellValue(cell));
			cell = row.getCell(4);
			webdrive1.findElement(By.name("enterAddressPostalCode")).sendKeys(formatter.formatCellValue(cell));
			webdrive1.findElement(By.name("enterAddressAddressLine1")).sendKeys(row.getCell(5).getStringCellValue());
			webdrive1.findElement(By.name("enterAddressAddressLine2")).sendKeys(row.getCell(6).getStringCellValue());
			webdrive1.findElement(By.name("enterAddressCity")).clear();
			webdrive1.findElement(By.name("enterAddressCity")).sendKeys(row.getCell(7).getStringCellValue());
			webdrive1.findElement(By.name("enterAddressStateOrRegion")).sendKeys(row.getCell(8).getStringCellValue());
			
			elem=webdrive1.findElement(By.name("AddressType"));
			Select select=new Select(elem);
			select.selectByValue("RES");
			elem=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"newShippingAddressFormFromIdentity\"]/div[1]/div[1]/form/div[5]/span/span/input")));
			elem.click();
			wait=new WebDriverWait(webdrive1, 5);
			try {
				do{
			
					try {
							wait.until(ExpectedConditions.visibilityOf(webdrive1.findElement(By.xpath("//span[@id='pp-ps-100-announce']"))));
							break;
					}
					catch(Exception e) {
						System.out.println("Error"+e.getMessage());
					}
					elem=webdrive1.findElement(By.xpath("//*[@id=\"newShippingAddressFormFromIdentity\"]/div[1]/div[1]/form/div[5]/span/span/input"));
					elem.click();
				}while(true);
			}
			catch(Exception e) {
				System.out.println("Error"+e.getMessage());
			}
			
			webdrive1.get(val);
			workbook.close();
		}
		
	}
	
	public void screenShot(String ssName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) webdrive1;
		File src=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("Res/Screenshots/"+ssName+".jpg"));
	}
	
	@AfterSuite
	public void closeDriver() throws InterruptedException {
		System.out.println(webdrive1);
		Thread.sleep(10000);
		webdrive1.quit();
	}
}
