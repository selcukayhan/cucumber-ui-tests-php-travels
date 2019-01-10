package steps;

import configuration.Config;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import objects.User;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static objects.UserPool.*;

public class LoginSteps {
    public WebDriver driver;

    public LoginSteps() {
        driver = Hooks.driver;
    }


    @When("^I log in using valid credentials$")
    public void iLogInUsingValidCredentials() {
        User user = defaultUser(driver).login();
    }

    @When("^I log in using invalid credentials$")
    public void iLogInUsingInValidCredentials() {
        User user = invalidLoginUser(driver).login();
    }

    @Then("^I log out$")
    public void iLogOut() {
        User user = defaultUser(driver).logout();

    }

    @Then("^As a (.*) user I should not be able to login on (.*) site")
    public void iShouldTryToLoginInPage(String user, String page) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        if (page.equals("defaultUserPage")) {
            if (user.equals("admin")) {
                adminUser(driver).login();
                Assert.assertEquals("MY ACCOUNT", driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).getText());
                Assert.assertTrue(driver.findElement(By.id("loginfrm")).isDisplayed());

            } else if (user.equals("supplier")) {
                supplierUser(driver).login();
                Assert.assertEquals("MY ACCOUNT", driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).getText());
                Assert.assertTrue(driver.findElement(By.id("loginfrm")).isDisplayed());
            }
        } else if (page.equals("supplierPage")) {
            if (user.equals("admin")) {
                adminUser(driver).supplierLogin();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/form[1]/div[2]/div")));
                Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/form[1]/div[2]/div")).isDisplayed());

            } else if (user.equals("defaultUser")) {
                defaultUser(driver).supplierLogin();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/form[1]/div[2]/div")));
                Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/form[1]/div[2]/div")).isDisplayed());
            }
        } else if (page.equals("adminPage")){
            if (user.equals("supplier")) {
                supplierUser(driver).adminLogin();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/form[1]/div[2]/div")));
                Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/form[1]/div[2]/div")).isDisplayed());

            } else if (user.equals("defaultUser")) {
                defaultUser(driver).adminLogin();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/form[1]/div[2]/div")));
                Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/form[1]/div[2]/div")).isDisplayed());
            }
        }
    }
}

