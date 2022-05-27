package SetupProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Setup {
	public WebDriver driver = null;

	@BeforeMethod
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", "G:\\ChromeDriver\\Version101\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://shopmrkatsu.tk/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void TearDown() throws Exception {
		driver.quit();
		// driver.close();

	}

}
