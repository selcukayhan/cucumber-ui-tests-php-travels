package configuration;

public abstract class Config {

    public static final String MAIN_URL = "https://www.phptravels.net/";
    public static final String SUPPLIER_URL = "https://www.phptravels.net/supplier";
    public static final String CMS_URL = "https://www.phptravels.net/admin";


    public static final int WAITTIME_TIMEOUT = 60;
    public static final int WAITTIME_ELEMENTOCCURENCE = 15;
    public static final int WAITTIME_SMALL = 5;

    public static final String DEFAULT_USERNAME = "JOHNY";
    public static final String DEFAULT_EMAIL = "user@phptravels.com";
    public static final String DEFAULT_PASSWORD = "demouser";
    public static final String INVALID_PASSWORD = "invalid";
    public static final String LOGIN_ERROR_MESSAGE = "Invalid Email or Password";

    public static final String ADMIN_USERNAME = "ADMIN";
    public static final String ADMIN_EMAIL = "admin@phptravels.com";
    public static final String ADMIN_PASSWORD = "demoadmin";

    public static final String SUPPLIER_USERNAME = "SUPPLIER";
    public static final String SUPPLIER_EMAIL = "supplier@phptravels.com";
    public static final String SUPPLIER_PASSWORD = "demosupplier";

    private Config() {
    }
}
