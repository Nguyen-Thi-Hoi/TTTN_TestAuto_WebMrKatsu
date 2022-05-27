package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkout_NoLogin {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");
		// set thời gian đi tìm locator
	}

	@Test
	public void TC_01_Checkout() throws InterruptedException {
		// Them vao gio hang thanh cong
		Thread.sleep(2000);
		driver.findElement(By.className("add-to-cart")).click();
		// Vao gio hang
		Thread.sleep(2000);
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
		// Click button Checkout
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[2]/section[3]/div[1]/div[2]/div[2]/div[1]/a[1]")).click();
		// Click button OK trên popup
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();

		// Popup khi Click button Check khi chưa Login
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.id("swal2-title")).getText(), "You Must Login To The Checkout");
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
}
