package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkout {
	WebDriver driver;

	@BeforeTest
	public void Init() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"G:\\ChromeDriver\\Version101\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");

		// Dang nhap
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("user_email")).sendKeys("banhgaomummim@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Banhgao@123");
		driver.findElement(By.name("commit")).click();
		
		// Them vao gio hang thanh cong
		Thread.sleep(2000);
		driver.findElement(By.className("add-to-cart")).click();
		// Vao gio hang
		Thread.sleep(2000);
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
	}

	@BeforeMethod
	public void beforMethod() {
		driver.navigate().refresh();
	}
	
	@Test
	public void TC_01_Checkout_No_Insert_Address() throws InterruptedException { // đã login ' fail
		// dien thong tin 
		Thread.sleep(2000);
		driver.findElement(By.className("btn-warning")).click();
		Thread.sleep(2000);
	    driver.findElement(By.id("user_address")).sendKeys("Dong Ke - Da Nang");
		 // Click button Update
	    Thread.sleep(2000);
		  driver.findElement(By.className("btn-primary")).click();
		// Click button Checkout
		driver.findElement(By.id("btnFetch")).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.className("swal2-title")).getText(), "Order Success!");

	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
}
