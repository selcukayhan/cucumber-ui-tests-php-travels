package objects;

import org.openqa.selenium.WebDriver;


public class User {

    private String email;
    private String password;
    private String name;
    private final WebDriver driver;

    public User(String email, String password, String name, WebDriver driver) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.driver = driver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User login() {
        new HomePage(driver)
                .callLoginPage()
                .login(this);
        return this;
    }

    public User supplierLogin() {
        new HomePage(driver)
                .callSupplierLoginPage()
                .supplierOrAdminLogin(this);
        return this;
    }

    public User adminLogin() {
        new HomePage(driver)
                .callAdminLoginPage()
                .supplierOrAdminLogin(this);
        return this;
    }

    public User logout() {
        new HomePage(driver)
                .logout();
        return this;
    }

    public User searchHotels(String city, String checkIn, String checkOut) {
        new HomePage(driver)
                .searchHotels(city, checkIn, checkOut);

        return this;
    }

    public User filter5star() {
        new HomePage(driver).filter5Starts();

        return this;
    }

    public User myProfile() {
        new HomePage(driver)
                .callMyProfilePage();
        return this;
    }

    public User myProfileFromUrl() {
        new HomePage(driver)
                .callMyProfilePageFromUrl();
        return this;
    }

    public User bookFirstHotelInResults() {
        new HomePage(driver).bookFirstHotel();

        return this;
    }

    public User changePhoneNumber(String phoneNumber) {
        new MyProfilePage(driver)
                .changePhoneNumberTo(phoneNumber);

        return this;
    }

    public boolean checkBook() {
        new MyProfilePage(driver).goToMyProfilePage().checkLastBook();

        return true;
    }

    public User adminAddMonksPalace() {
        new HomePage(driver)
                .callHotelsPage()
                .addMonksPalace();

        return this;
    }
}





















