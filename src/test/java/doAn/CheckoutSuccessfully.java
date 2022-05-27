package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckoutSuccessfully {
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
		driver.findElement(By.id("user_email")).sendKeys("nthoi2kk@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Abcdefgh@123");
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
	public void TC_01_Checkout_Successfully() throws InterruptedException { // đã login
		// Chon dich vu giao hang; viettel post - $30.0 - Nhận hàng từ 3-5 ngày ; giao
		// hang tiet kiem - $49.0 - Nhận hàng từ 2-3 ngày
		Thread.sleep(3000);
		Select dropService = new Select(driver.findElement(By.className("form-select")));
		dropService.selectByVisibleText("viettel post - $30.0 - Nhận hàng từ 3-5 ngày");
		// Nhap Voucher
		// driver.findElement(By.className("voucher_code")).sendKeys("giamgia");
		// Click button Checkout
		Thread.sleep(2000);
		driver.findElement(By.id("btnFetch")).click();
		// Click button OK sau khi thanh toán thành công
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.className("swal2-title")).getText(), "Order Success!");
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}

}
