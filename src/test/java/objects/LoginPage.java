package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameTextBox;

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(xpath = "/html/body/div/form[1]/button")
    private WebElement supplierLogInButton;


    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//*[@id=\"loginfrm\"]/button")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"loginfrm\"]/div/div[2]/div")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage login(User user) {
        typeTo(usernameTextBox, user.getEmail());
        typeTo(passwordTextBox, user.getPassword());
        clickTo(signInButton);

        return new HomePage(driver);
    }

    public HomePage supplierOrAdminLogin(User user) {
        typeTo(emailTextBox, user.getEmail());
        typeTo(passwordTextBox, user.getPassword());
        clickTo(supplierLogInButton);

        return new HomePage(driver);
    }


}
