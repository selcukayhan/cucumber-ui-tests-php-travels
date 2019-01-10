package steps;

import configuration.Config;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get(Config.MAIN_URL);
        driver.manage().deleteAllCookies();
        driver.getWindowHandle();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
