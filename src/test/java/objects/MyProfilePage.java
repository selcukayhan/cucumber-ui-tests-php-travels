package objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends BasePage {

    @FindBy(className = "panel-body")
    private WebElement profileSettings;

    @FindBy(className = "profile-icon")
    private WebElement myAccount;

    @FindBy(className = "accountresult")
    private WebElement updateResult;

    @FindBy(xpath = "//*[@id=\"profilefrm\"]/div/div[3]/div[3]/button")
    private WebElement saveButton;

    @FindBy(name = "phone")
    private WebElement saveProfileChanges;

    @FindBy(name = "phone")
    private WebElement phoneNumberField;

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    public HomePage myProfile(User user) {
        clickTo(myAccount);
        waitObject(By.className("profileSettings"));

        return new HomePage(driver);
    }

    public MyProfilePage goToMyProfilePage() {
        driver.get("https://www.phptravels.net/account");

        return new MyProfilePage(driver);
    }

    public HomePage checkLastBook() {

        System.out.println("selam" + driver.findElement(By.xpath("//*[contains(text(),'Booking Code ')]")).getText());
        System.out.println("  as" + bookId);

        Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(),'Booking Code ')]")).getText(), bookId);

        return new HomePage(driver);
    }

    public void changePhoneNumberTo(String newPhoneNumber) {
        clickTo(phoneNumberField);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(newPhoneNumber);
        clickTo(saveButton);

        waitObject(By.className("accountresult"));
    }
}
