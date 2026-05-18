package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductBrowsingTest extends BaseTest {

    @Test
    public void testSortByNameAtoZ() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.selectSortOption("za"); //First sort from Z to A, since by default the order is in Ascending,
        // from A to Z, then you can sort to A to Z after, to see whether it really works fine, and display the correct
        // information, or order of the products
        inventoryPage.selectSortOption("az");
        List<String> names = inventoryPage.getAllItemsNames();
        List<String> expectedSortedNames = new ArrayList<>(names);
        Collections.sort(expectedSortedNames);
        Assert.assertEquals(names, expectedSortedNames, "Items are not sorted A-Z!");
    }

    @Test
    public void testSortByNameZtoA() {
        //loginPage.login("standard_user", "secret_sauce"); //We can't login again here, and be come back
        inventoryPage.selectSortOption("za");
        List<String> names = inventoryPage.getAllItemsNames();
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames, Collections.reverseOrder());

        Assert.assertEquals(names, sortedNames, "Items are not sorted Z-A!");
    }

    @Test
    public void testSortByPriceLowToHigh() {
        //loginPage.login("standard_user", "secret_sauce"); //We don't need to login again
        inventoryPage.selectSortOption("lohi");
        List<Double> prices = inventoryPage.getAllItemsPrices().stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);

        Assert.assertEquals(prices, sortedPrices, "Prices are not sorted Low to High!");
    }

    @Test
    public void testSortByPriceHighToLow() {
        // loginPage.login("standard_user", "secret_sauce"); // We don't need to login again here as well, since we already logged in before, inthe beginning
        inventoryPage.selectSortOption("hilo");
        List<Double> prices = inventoryPage.getAllItemsPrices().stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());

        Assert.assertEquals(prices, sortedPrices, "Prices are not sorted High to Low!");
    }
}