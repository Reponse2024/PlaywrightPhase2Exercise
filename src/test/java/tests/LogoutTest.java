package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    @Test
    public void testUserCanLogoutSuccessfully() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(page.url().contains("inventory.html"), "Login failed!");
        nav.logout();
        Assert.assertTrue(page.isVisible("#login-button"), "Logout failed - Login button not visible!");
        Assert.assertEquals(page.url(), "https://www.saucedemo.com/", "URL did not match login page!");
    }
}