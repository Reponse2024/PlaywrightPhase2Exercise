package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductCartTest extends BaseTest {
    @Test
    public void testAddProductToCart() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addProductToCart();
        String badgeCount = page.innerText(".shopping_cart_badge");
        Assert.assertEquals(badgeCount, "1");
    }
}