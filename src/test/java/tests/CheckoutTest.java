package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    @Test
    public void testCompletePurchaseFlow() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addProductToCart();
        inventoryPage.goToCart();
        checkoutPage.proceedToCheckout();
        checkoutPage.fillInformation("Reponse", "Dev", "RW123");
        checkoutPage.finishCheckout();

        Assert.assertEquals(checkoutPage.getSuccessMessage(), "Thank you for your order!");
    }
}