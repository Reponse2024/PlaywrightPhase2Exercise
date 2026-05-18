package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class SauceDemoTest {
    Playwright playwright;
    Browser browser;
    Page page;

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CheckoutPage checkoutPage;

    @DataProvider(name = "userDiscovery")
    public Object[][] userTestData() {
        return new Object[][] {
                { "standard_user" },
//                { "problem_user" },
//                { "performance_glitch_user" },
//                { "error_user" },
//                { "visual_user" }
        };
    }

    @BeforeClass
    public void setup(){
       playwright= Playwright.create();
       browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
       page = browser.newPage();

        loginPage = new LoginPage(page);
        inventoryPage = new InventoryPage(page);
        checkoutPage = new CheckoutPage(page);

        page.navigate("https://www.saucedemo.com");
    }
    @Test(dataProvider = "userDiscovery")
    public void testCompleteOrderFlow(String username){
       loginPage.login(username,"secret_sauce");

        inventoryPage.addProductToCart();
        inventoryPage.goToCart();

        checkoutPage.proceedToCheckout();
        checkoutPage.fillInformation("Reponse", "Iduha", "1234569");
        checkoutPage.finishCheckout();

        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!");

        checkoutPage.gobackHome();
    }
    @AfterClass
    public void tearDown(){
        page.close();
        browser.close();
        playwright.close();
    }
}
