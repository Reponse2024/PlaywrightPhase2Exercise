package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
public class AuthenticationTest extends BaseTest {
    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(page.url().contains("inventory.html"));
    }
    @Test
    public void testLockedOutUser() {
        loginPage.login("locked_out_user", "secret_sauce");
        String error = page.innerText("h3[data-test='error']");
        Assert.assertTrue(error.contains("Sorry, this user has been locked out"));
    }
}