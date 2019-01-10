package objects;

import configuration.Config;
import org.openqa.selenium.WebDriver;

public class UserPool {

    private UserPool() {
    }

    public static User defaultUser(WebDriver driver) {
        return new User(Config.DEFAULT_EMAIL, Config.DEFAULT_PASSWORD, Config.DEFAULT_USERNAME, driver);
    }

    public static User invalidLoginUser(WebDriver driver) {
        return new User(Config.DEFAULT_EMAIL, Config.INVALID_PASSWORD, Config.DEFAULT_USERNAME, driver);
    }

    public static User supplierUser(WebDriver driver) {
        return new User(Config.DEFAULT_EMAIL, Config.INVALID_PASSWORD, Config.DEFAULT_USERNAME, driver);
    }

    public static User adminUser(WebDriver driver) {
        return new User(Config.DEFAULT_EMAIL, Config.INVALID_PASSWORD, Config.DEFAULT_USERNAME, driver);
    }
}
