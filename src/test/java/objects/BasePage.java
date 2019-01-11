package objects;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {

    @FindBy(linkText = "MY ACCOUNT")
    private WebElement headerBar;

    @FindBy(linkText = "JOHNY")
    private WebElement headerBarLogin;

    @FindBy(linkText = "Logout")
    private WebElement logout;

    @FindBy(linkText = "Login")
    private WebElement dropDownLoginButton;

    @FindBy(id = "searchData")
    private WebElement searchBar;

    @FindBy(className = "nav-action-inner")
    private WebElement signInButton;

    @FindBy(className = "searchBtn")
    private WebElement searchButton;

    @FindBy(css = "a.menuLink.user")
    private WebElement username;

    @FindBy(className = "profile-icon")
    private WebElement myAccount;

    @FindBy(xpath = "//*[@title='Favorilerim']")
    private WebElement myFavorites;

    @FindBy(xpath = "/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[2]/a")
    private WebElement logoutButton;

    @FindBy(xpath = "/html/body/nav/div/div[1]/a")
    private WebElement brandLogo;

    @FindBy(className = "select2-chosen")
    private WebElement hotelSearch;

    @FindBy(xpath = "//*[@id=\"dpd1\"]/div/input")
    private WebElement checkIn;

    @FindBy(xpath = "//*[@id=\"dpd2\"]/div/input")
    private WebElement checkout;

    @FindBy(xpath = "//*[@id=\"hotels\"]/form/div[5]")
    private WebElement hotelSearchButton;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li[2]/ul/li[1]/div/span")
    private WebElement locationDropdown;

    @FindBy(xpath = "//*[@id=\"collapse1\"]/div[1]/div/div[9]/label")
    private WebElement fivestar;

    @FindBy(id = "searchform")
    private WebElement filterSearch;

    @FindBy(xpath = "//*[@id=\"body-section\"]/div[5]/div/div[3]/div[1]/div/table/tbody/tr[1]/td/div[3]/a/button")
    private WebElement firstHotelInlist;

    @FindBy(xpath = "//*[@id=\"ROOMS\"]/div/table/tbody/tr[1]/td/div[2]/div[2]/div/div[3]/div/label/div")
    private WebElement cheapestRoom;

    @FindBy(xpath = "//*[@id=\"ROOMS\"]/div/button")
    private WebElement bookNowButton;

    @FindBy(xpath = "//*[@id=\"body-section\"]/div[1]/div/div[1]/div/div[3]/button")
    private WebElement confirmBooking;

    @FindBy(xpath = "//*[@id=\"invoiceTable\"]/tbody/tr[2]/td/div[1]/table/tbody/tr/td/div[3]")
    private WebElement invoice;

    @FindBy(id = "Hotels")
    private WebElement hotelsDropdown;

    @FindBy(xpath = "//*[@id=\"Hotels\"]/li[1]/a")
    private WebElement hotelsFromDropdown;

    @FindBy(xpath = "//*[@id=\"content\"]/div/form/button")
    private WebElement addButton;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public static String bookId;

    public LoginPage callLoginPage() {
        if (headerBar.isDisplayed()) {
            clickTo(headerBar);
            clickTo(dropDownLoginButton);
            return new LoginPage(driver);
        } else {
            clickTo(logout);
            return new LoginPage(driver);
        }
    }

    public LoginPage callSupplierLoginPage() {
        driver.get(Config.SUPPLIER_URL);
        return new LoginPage(driver);
    }

    public LoginPage callAdminLoginPage() {
        driver.get(Config.CMS_URL);
        return new LoginPage(driver);
    }

    public HomePage logout() {
        clickTo(headerBarLogin);
        clickTo(logoutButton);
        return new HomePage(driver);
    }

    public HomePage searchHotels(String city, String checkInDate, String checkOutDate) {
        clickTo(brandLogo);
        typeToWithAction(hotelSearch, city);
        clickTo(locationDropdown);
        typeToWithAction(checkIn, checkInDate);
        typeToWithAction(checkout, checkOutDate);
        clickTo(hotelSearchButton);

        return new HomePage(driver);
    }

    public HomePage filter5Starts() {
        clickTo(fivestar);
        clickTo(filterSearch);

        return new HomePage(driver);
    }

    public HomePage bookFirstHotel() {
        clickTo(firstHotelInlist);
        scrolldown();
        clickTo(cheapestRoom);
        clickTo(bookNowButton);
        scrolldown();
        clickTo(confirmBooking);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[2]/td/div[1]/table/tbody/tr/td/div[5]")));

        bookId = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[2]/td/div[1]/table/tbody/tr/td/div[5]")).getText();
        System.out.println(bookId);

        return new HomePage(driver);
    }


    public MyProfilePage callMyProfilePage() {
        clickTo(myAccount);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"profilefrm\"]/div/div[1]/div[1]/h3"), "Personal Details");

        return new MyProfilePage(driver);
    }

    public MyProfilePage callMyProfilePageFromUrl() {
        driver.get("https://www.phptravels.net/Account");

        return new MyProfilePage(driver);
    }

    public HomePage callHotelsPage() {
        driver.get("https://www.phptravels.net/admin/hotels");

        return new HomePage(driver);
    }

    public HomePage addMonksPalace() {
        clickTo(addButton);
        typeToWithAction(driver.findElement(By.name("hotelname")), "Monks Palace");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@title,'Rich Text Editor, hoteldesc')]")));
        WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@title,'Rich Text Editor, hoteldesc')]"));
        driver.switchTo().frame(iframe);
        typeToWithAction(driver.findElement(By.xpath("/html/body/p")), "selam adadsasd a");
        driver.switchTo().defaultContent();
        scrolldown();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mapaddress")));
        typeToWithAction(driver.findElement(By.partialLinkText("Enter Location")), "Hilversum");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div/span")));
        clickTo(driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div/span")));

        scrolldown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add\"]")));

        clickTo(By.xpath("//*[@id=\"content\"]/form/div/ul/li[2]/a"));

        clickTo(driver.findElement(By.xpath("//*[@id=\"FACILITIES\"]/div/div/div[1]/label")));

        clickTo(driver.findElement(By.xpath("//*[@id=\"FACILITIES\"]/div/div/div[8]/label")));

        clickTo(driver.findElement(By.xpath("//*[@id=\"add\"]")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Monks Palace")));

        return new HomePage(driver);
    }
}
