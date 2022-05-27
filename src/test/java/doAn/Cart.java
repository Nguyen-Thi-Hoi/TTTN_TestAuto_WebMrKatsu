package doAn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Cart {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");

		// Dang nhap
		// driver.findElement(By.linkText("Login")).click();
		// driver.findElement(By.id("user_email")).sendKeys("nthoi2kk@gmail.com");
		// driver.findElement(By.id("user_password")).sendKeys("Abcdefgh@123");
		// driver.findElement(By.name("commit")).click();
	}

	@BeforeMethod
	public void beforMethod() {
		driver.navigate().refresh();
	}

	@Test
	public void TC_01_AddToCart() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//body/div[2]/section[2]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/a[2]"))
				.click();
		// vào icon giỏ hàng
		Thread.sleep(2000);
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
	}

	@Test
	public void TC_02_AddToCart_ProductDetail() throws InterruptedException { // click View Quick từ Homepage
		Thread.sleep(2000);
		driver.findElement(By
				.xpath("//body/div[2]/section[2]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]/div[1]/div[1]/a[1]/img[1]"))
				.click();
		// Set thời gian chờ tim element
		Thread.sleep(2000);
		// Nhap luong san pham
		driver.findElement(By.cssSelector("#numberInput")).clear();
		driver.findElement(By.cssSelector("#numberInput")).sendKeys("10");
		// chon Size XL
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'XL')]")).click();
		// Them vao gio hang
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='add-to-cart-detail']")).click();
		// vào icon giỏ hàng
		Thread.sleep(2000);
		driver.navigate().to("http://shopmrkatsu.tk/en/users/carts");
	}

	@Test
	public void TC_08_AddToCart_InputQuantity_Character() throws InterruptedException {
		// click View Quick từ Homepage
		driver.findElement(
				By.xpath("//body/div[2]/section[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[2]/a[1]"))
				.click();
		// Set thời gian chờ tim element
		Thread.sleep(3000);
		// Nhap soluong san pham
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#numberInput")).clear();
		driver.findElement(By.cssSelector("#numberInput")).sendKeys("10e");
		// chon Size XL
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div[3]"))
				.click();
		// Them vao gio hang
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/span[2]/button[1]"))
				.click();
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@id='swal2-title']")).getText(),
				"Quantity must be number");
	}

	@Test
	public void TC_05_Delete_Item_Product() throws InterruptedException { // Vao trang gio hang
		Thread.sleep(3000);
		driver.findElement(By.xpath("//header/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]")).click();
		driver.findElement(By.className("cart_quantity_delete")).click();
	}

	@Test
	public void TC_02_Plus_Product() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.className("cart_quantity_up")).click();
	}

	@Test
	public void TC_03_Minus_Product() throws InterruptedException {
		driver.findElement(By.cssSelector(
				"div.show_body:nth-child(5) section.col-sm-offset-1.col-sm-10:nth-child(2) div.container div.table-responsive.cart_info table.table.table-condensed tbody.tbody:nth-child(2) tr.list-cart:nth-child(1) td.cart_quantity div.cart_quantity_button > button.cart_quantity_down"))
				.click();
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click(); //
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.id("swal2-title")).getText(), "Quantity should not be less than 1");
	}

	@Test
	public void TC_04_Enter_Quality_Product() {
		driver.findElement(By.className("cart_quantity_input")).clear();
		driver.findElement(By.className("cart_quantity_input")).sendKeys("4");
	}

	@Test
	public void TC_06_Clear_All_Product() {
		driver.findElement(By.id("clear_cart")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Shopping cart is empty')]")).getText(),
				"Shopping cart is empty");
	}

	@AfterTest
	public void Close() {
		// driver.quit();
	}

	// Dùng sleep để set cứng tránh tình trạng mạng chậm trình duyệt nhảy lung tung
	// các bước trong TC dẫn đến TC fail
	/*
	 * public void sleepInSecond(long timeInSecond) { try {
	 * Thread.sleep(timeInSecond * 1000); } catch (InterruptedException e) {
	 * e.printStackTrace(); } }
	 */
}
