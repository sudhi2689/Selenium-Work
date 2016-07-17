package bo;

import static org.testng.AssertJUnit.assertTrue;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import com.thoughtworks.selenium.webdriven.commands.IsTextPresent;

import util.AppProperties;
import util.SeleniumDriverUtil;

public class LoginScreen {
	WebDriver driver = null;
	Logger logger = Logger.getLogger(LoginScreen.class);
	AppProperties prop = null;
	
	public LoginScreen()
	{
		SeleniumDriverUtil util = new SeleniumDriverUtil();
		this.prop = new AppProperties();
		this.driver = util.getWebDriver(prop.getProperty("browser"));
	}
	
	public void navigateTo() throws Exception
	{
		driver.navigate().to(prop.getProperty("url"));
		Thread.sleep(5000);
	}
	
	public void login()
	{
		logger.info("Logging in to App...");
		driver.findElement(By.cssSelector("input[name=username]")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.cssSelector("input[name=password]")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.cssSelector("input[name=login]")).click();
		logger.info("Logged in successfully");
	}
	
	public void logout()
	{
		logger.info("Logging out the app!!!");
		driver.findElement(By.xpath("//tbody/tr/td[2]/a[4]")).click();
		driver.quit();
	}
	
	public void closeWindow()
	{
		driver.quit();
	}
	
	public void verifySearchButton() throws Exception
	{
		driver.findElement(By.cssSelector("select[name=location]")).click();
//		driver.findElement(By.cssSelector("option[value=Brisbane]")).click();
		Thread.sleep(5000);
//		driver.findElement(By.xpath("//select[@id='location']/option[5]")).click();
		driver.findElement(By.xpath("//select[@id='location']/option[@value='Adelaide']")).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		/*Actions builder = new Actions(driver);
		builder.keyDown(Keys.ENTER).build().perform();
		builder.keyUp(Keys.ENTER).build().perform();*/
		driver.findElement(By.cssSelector("input[value=Search]")).click();
		Thread.sleep(5000);
		logger.info("isTextPresent(Select Hotel): "+isTextPresent("Select Hotel123"));
		Reporter.log("Verifying Assert");
		Assert.assertEquals("actual", "expected");
		Assert.assertTrue(driver.getPageSource().contains("Select Hotel123454"));
//		driver.quit();
		
	}
	
	protected boolean isTextPresent(String text){
	    try{
	        boolean b = driver.getPageSource().contains(text);
	        return b;
	    }
	    catch(Exception e){
	        return false;
	    }
	  }

}
