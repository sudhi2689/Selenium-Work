import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
//import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bo.LoginScreen;

import util.AppProperties;
import util.ErrorUtil;
import util.LogClass;
import util.SeleniumDriverUtil;


public class TestNGTest {
	LoginScreen screen = new LoginScreen();
	
	Logger log = Logger.getLogger(TestNGTest.class);
	boolean loggedIn = false;
	
	
	@BeforeClass(alwaysRun=true)
	public void init() throws Exception
	{
		log.info("Inside init()....");
		if(!loggedIn)
		{
			screen.navigateTo();
			screen.login();
			loggedIn = true;
		}
	}
	@Test(groups = { "functest1"})
	public void testSearchButton() {
		try
		{
		log.info("testSearchButton started...");
		screen.verifySearchButton();
		log.info("Ending testSearchButton");
		}catch(Throwable th)
		{
//			log.info("Exception: "+th);
			ITestResult  itr = Reporter.getCurrentTestResult();
			itr.setStatus(ITestResult.FAILURE);
			Reporter.setCurrentTestResult(itr);
			log.info("Test "+itr.getName()+ " Failed due to "+th.toString());
			
			//ErrorUtil.addFailure(th);
			if(loggedIn)
			{
				screen.logout();
				loggedIn = false;
			}else
				screen.closeWindow();
		}
	}
	
	@Test(groups = { "functest"})
	public void testChangePasswordLink() throws Exception{
	/*	System.out.println("Starting Test method 2");
//		driver = SeleniumDriverUtil.getWebDriver("");
//		driver.get("https://www.google.co.in");
//		Thread.sleep(10000);
		driver.findElement(By.cssSelector("input[title=Search]")).sendKeys("Hello Google");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("button[value=Search]")).click();
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.id("resultStats")).getText());
//		driver.close();
//		Thread.sleep(5000);
		driver.quit();*/
			
		
		System.out.println("Ending testMeth2");		
	}

}
