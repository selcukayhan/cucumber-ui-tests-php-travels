package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import objects.MyProfilePage;
import objects.User;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static objects.UserPool.defaultUser;
import static objects.UserPool.invalidLoginUser;

public class MyProfileSteps {
    public WebDriver driver;

    public MyProfileSteps() {
        driver = Hooks.driver;
    }


    @When("^I want to visit my profile settings$")
    public void iWantToVisitMyProfileSettings() {
        User user = defaultUser(driver).myProfile();
    }


    @Then("^I want to change phone number to \"([^\"]*)\"$")
    public void iWantToChangePhoneNumberTo(String phoneNumber){
        defaultUser(driver).changePhoneNumber(phoneNumber);
    }

    @Then("^My phone number has to be changed to \"([^\"]*)\"$")
    public void myPhoneNumberHasToBeChangedTo(String phoneNumber){
        Assert.assertEquals(phoneNumber,driver.findElement(By.name("phone")).getAttribute("value"));

    }

    @Then("^My book should be in my account with same Book id$")
    public void myBookShouldBeInMyAccountWithSameBookId() {
        defaultUser(driver).myProfileFromUrl();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookings")));
        Assert.assertTrue(defaultUser(driver).checkBook());

    }
}

