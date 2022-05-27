package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Login {
	WebDriver driver;
	
    @BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");
	}	


	@Test
	public void TC_01_Login_Empty_Email_Password() {
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("commit")).click();

		Assert.assertEquals(driver.findElement(By.className("alert")).getText(), "Invalid Email or password.");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_email")).sendKeys("nthoi@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Abcdefgh@123");
		driver.findElement(By.name("commit")).click();
		                  // (actual , expected)
		Assert.assertEquals(driver.findElement(By.id("alert")).getText(), "Invalid Email or password.");
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_email")).sendKeys("nthoi2kk@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Abcdefg");
		driver.findElement(By.name("commit")).click();
		Assert.assertEquals(driver.findElement(By.id("alert")).getText(), "Invalid Email or password.");
	}
	
    @Test
	public void TC_04_LoginSuccessFully() throws InterruptedException {
		// Vao trang login
    	Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		// Nhap Username - Password
		Thread.sleep(2000);
		driver.findElement(By.id("user_email")).sendKeys("nthoi2kk@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("user_password")).sendKeys("Abcdefgh@123");

		driver.findElement(By.name("commit")).click();
		// Đăng xuất 
	    // driver.findElement(By.linkText("Logout")).click();
		
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(), "Signed in successfully.");
	}
    
//	@Test
//	public void TC_05_Login_Incorect_Email_Password() {
//		driver.findElement(By.linkText("Login")).click();
//
//		driver.findElement(By.id("user_email")).sendKeys("nthoi");
//		driver.findElement(By.id("user_password")).sendKeys("35dfhd");
//		driver.findElement(By.name("commit")).click();
//		Assert.assertEquals(driver.findElement(By.id("alert")).getText(), "Invalid Email or password.");
//	}
	
	@AfterTest
	public void CloseTest() {
		// driver.quit();
	}

}
