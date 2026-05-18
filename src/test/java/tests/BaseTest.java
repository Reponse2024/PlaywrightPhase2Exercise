package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class BaseTest {
    Playwright playwright;
    Browser browser;
    Page page;

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CheckoutPage checkoutPage;
    protected LoginPage nav;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();

        loginPage = new LoginPage(page);
        inventoryPage = new InventoryPage(page);
        checkoutPage = new CheckoutPage(page);
        nav = new LoginPage(page);

        page.navigate("https://www.saucedemo.com");
    }
    @AfterClass
    public void tearDown(){
        if(page != null) page.close();
        if(browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
