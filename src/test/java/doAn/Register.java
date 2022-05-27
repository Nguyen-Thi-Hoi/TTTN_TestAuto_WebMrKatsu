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

public class Register {
	WebDriver driver;

	@BeforeTest
	public void Init() {
		System.setProperty("webdriver.chrome.driver",
				"G:\\\\ChromeDriver\\\\Version101\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://shopmrkatsu.tk/");
	}

	@BeforeMethod
	public void beforMethod() {
		driver.navigate().refresh();
	}
	
	@Test
	public void TC_01_Empty_AllFields() throws InterruptedException  {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("error_explanation")).getText(), "Please fill in all the information");
		
	}
	
	@Test
	public void TC_02_Enter_Email_Exist() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		Thread.sleep(1000);
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthang@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		Thread.sleep(1000);
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");
		Thread.sleep(1000);
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Email has already been taken");
	}
	@Test
	public void TC_03_Enter_ConfirmPassword_Incorrect() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		Thread.sleep(1000);
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangxv@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		Thread.sleep(1000);
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Password confirmation doesn't match Password");
	}
	@Test
	public void TC_04_Register_Successfully() throws InterruptedException{
		// Vao trang Login
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		// Nhap User - Mobile - Email - Password - Confirm Password
		Thread.sleep(1000);
		driver.findElement(By.id("user_username")).sendKeys("banh gao");
		Thread.sleep(1000);
		driver.findElement(By.id("user_mobile")).sendKeys("0334875514");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("banhgaomummim@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Banhgao@123");
		Thread.sleep(1000);
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Banhgao@123");
		// Chon Thanh pho
		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Da Nang");
		// Click button Register
		driver.findElement(By.name("button")).click();
		// driver.findElement(By.linkText("Logout")).click();
		// So sanh ket qua thuc te - mong doi
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(),
				"Welcome! You have signed up successfully.");
	}

	
    /*
	@Test
	public void TC_03_Empty_UserName() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("");
		driver.findElement(By.id("user_mobile")).sendKeys("0334887439");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangF@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Username can't be blank");
	}

	@Test
	public void TC_04_Enter_Name_Special() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("Hoa@12#$");
		driver.findElement(By.id("user_mobile")).sendKeys("0334887439");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangE@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(), "Username can't be special");
	}

	@Test
	public void TC_05_Enter_Name_1_Charater() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("H");
		driver.findElement(By.id("user_mobile")).sendKeys("0334887439");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangE@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(),
				"Username is only allowed to enter from 2 to 15 characters");
	}

	@Test
	public void TC_06_Empty_Mobile() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangE@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Mobile can't be blank");
	}

	@Test
	public void TC_07_Enter_Mobile_Have_Special() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("033@#$%abv");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangD@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Mobile can not have special");
	}

	@Test
	public void TC_08_Enter_Mobile_Have_Space() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("033 488 7 ");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangD@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Mobile can not have space");
	}

	@Test
	public void TC_09_Enter_Mobile_Char_Number() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("033abcd12a");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangD@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Mobile can not have characters");
	}

	@Test
	public void TC_10_Enter_Mobile_Less_than_10_Digits() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("033");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangD@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Mobile is too short (minimum is 10 characters)");
	}

	// Run thành công
	@Test
	public void TC_11_Enter_Mobile_10_Digits() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("0334884691");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangA@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(),
				"Welcome! You have signed up successfully.");
	}

	@Test
	public void TC_12_Enter_Mobile_More_than_11_Digits() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("016392178547");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangB@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();

		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(), "Mobile Invalid");
	}

	@Test
	public void TC_13_Enter_Mobile_String_0() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("huu thang");
		driver.findElement(By.id("user_mobile")).sendKeys("00000000000");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangC@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		Select dropCity = new Select(driver.findElement(By.name("user[city_id]")));
		dropCity.selectByVisibleText("Hue");
		driver.findElement(By.name("button")).click();
		// Assert(actual,expected)
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(), "Mobile Invalid");
	}

	@Test
	public void TC_14_Enter__Email_Invalid() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("Abcd");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("thuy!$%^#sgsdg45@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("notice_wraper")).getText(), "Email invalid"); // login thành công

	}

	

	// Run thành công
	@Test
	public void TC_16_Enter_Email_more_than_35Character() { // Welcome you have successfully
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangdsfmdkjfklsdjfklsdfnkdsnflkdsnflkasnlksnkasfnasklfklas@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Email invalid");
	}

	@Test
	public void TC_17_Empty_Password() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangxyz@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("Huuthang@123");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(), "Password can't be blank");
	}

	@Test
	public void TC_18_Enter_Password_Less_than_6_Digits() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangx@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("123ab");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("123ab");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Password is too short (minimum is 6 characters)");
	}

	@Test
	public void TC_19_Enter_Password_Number() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangxq@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("123454245");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("123454245");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Please choose a stronger password (6 characters minimum,include special characters, numbers and uppercase letters)");
	}

	// Run thành công
	@Test
	public void TC_20_Enter_Password_Special() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangxqs@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("########");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("########");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Please choose a stronger password (6 characters minimum,include special characters, numbers and uppercase letters)");
	}

	@Test
	public void TC_21_Enter_Password_Number_Character() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangxqts@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("dgdsgq456");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("dgdsgq456");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Please choose a stronger password (6 characters minimum,include special characters, numbers and uppercase letters)");

	}

	@Test
	public void TC_22_Empty_ConfirmPassword() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangxyzr@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Password confirmation can't be blank");
	}

	

	@Test
	public void TC_24_Enter_ConfirmPassword_Uppercase() {
		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("user_username")).sendKeys("nthoi2k");
		driver.findElement(By.id("user_mobile")).sendKeys("0331447854");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[3]/input[1]"))
				.sendKeys("huuthangxv@gmail.com");
		driver.findElement(By.xpath("//body/div[2]/section[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[4]/input[1]"))
				.sendKeys("Huuthang@123");
		driver.findElement(By.id("user_password_confirmation")).sendKeys("HUUTHANG@123");

		driver.findElement(By.name("button")).click();
		Assert.assertEquals(driver.findElement(By.id("error_explanation")).getText(),
				"Password confirmation doesn't match Password");
	}
    */
	
	@AfterTest
	public void Close() {
		// driver.quit();
	}

}
