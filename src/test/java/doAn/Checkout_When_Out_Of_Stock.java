package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Checkout_When_Out_Of_Stock {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		// Khai báo đường dẫn cho webdriver -> để script có thể chạy
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		// khai báo driver tương ứng với trình duyệt muốn test
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");
        // Sử dụng các phương thức của webdriver để làm việc 
		// Dang nhap
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("user_email")).sendKeys("nthoi2kk@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Abcdefgh@123");
		driver.findElement(By.name("commit")).click();

	}

//	@BeforeMethod
//	public void beforMethod() {
//		driver.navigate().refresh();
//	}

	@Test
	public void TC_01_Checkout_When_Out_Of_Stock() throws InterruptedException { // Đã login ; nhập địa chỉ
		// click View Quick từ Homepage 
	      Thread.sleep(2000);
		  driver.findElement(By.id("a")).click();
		  // Set thời gian chờ tim element 
		  Thread.sleep(3000); 
		  // Nhap soluong san pham 
		  driver.findElement(By.cssSelector("#numberInput")).clear();
		  driver.findElement(By.cssSelector("#numberInput")).sendKeys("100"); 
		  // chon Size XL 
		  driver.findElement( By.xpath(
		  "//body/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div[3]"
		  )) .click(); 
		  // Them vao gio hang 
		  driver.findElement(By.xpath(
				  "//body/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/span[2]/button[1]")).click();
		// Vao gio hang
			driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
		// Click button Checkout
		driver.findElement(By.id("btnFetch")).click();
		// Hien thi popup
		Thread.sleep(3000);
		 Assert.assertEquals(driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText(),
		 		"Shopping cart is invalid or Out of stock");
		// Click button OK sau khi thanh toán thành công
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}
	
}
