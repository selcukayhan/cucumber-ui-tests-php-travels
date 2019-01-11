package steps;


import configuration.Config;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import objects.User;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static objects.UserPool.*;

public class MainSteps {

    public WebDriver driver;

    public MainSteps() {
        driver = Hooks.driver;
    }

    @Given("^I am a user of phpTravels\\.com$")
    public void iAmAUserOfPhpTravelsCom() {
        User user = defaultUser(driver);
    }

    @Given("^I am an admin user of phpTravels\\.com$")
    public void iAmAnAdminUserOfPhpTravelsCom() {
        User user = adminUser(driver);
    }

    @Then("^I should be logged in$")
    public void iShouldBeLoogedIn() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a"), Config.DEFAULT_USERNAME));
        Assert.assertEquals(Config.DEFAULT_USERNAME, driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).getText());

    }

    @Then("^I should not be logged in$")
    public void iShouldNotBeLoggedIn() {
        Assert.assertEquals("MY ACCOUNT", driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).getText());
        Assert.assertTrue(driver.findElement(By.id("loginfrm")).isDisplayed());
    }

    @And("^I searches hotels in the \"([^\"]*)\" with check in date \"([^\"]*)\" and checkout date \"([^\"]*)\"$")
    public void iSearchesHotelsInThe(String city, String checkIn, String checkOut) {
        defaultUser(driver).searchHotels(city, checkIn, checkOut).filter5star();
    }

    @Then("^I book cheapest room of cheapest hotel$")
    public void iBookCheapestRoomOfCheapestHotel() {
        defaultUser(driver).bookFirstHotelInResults();
    }

    @Then("^I should see the invoice$")
    public void iShouldSeeTheInvoice() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[2]/td/div[1]/table/tbody/tr/td/div[3]")));

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[2]/td/div[1]/table/tbody/tr/td/div[3]")).isDisplayed());
    }

    @Given("^I am an (.*) user of phpTravels$")
    public void iAmAnUserOfPhpTravels(String userType) {
        if (userType.equals("admin")) {
            adminUser(driver);
        } else if (userType.equals("supplier")) {
            supplierUser(driver);
        } else
            defaultUser(driver);
    }

    @Then("^I want to add Monks Palace as a Hotel$")
    public void iWantToAddMonksPalaceAsAHotel() {
        adminUser(driver)
                .adminLogin()
                .adminAddMonksPalace();
    }
}
