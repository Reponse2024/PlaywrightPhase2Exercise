package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class InventoryPage {
    private Page page;
    private String sortContainer = ".product_sort_container";
    private String itemNames = ".inventory_item_name";
    private String itemPrices = ".inventroy_item_price";
    private String backpackAddButton = "#add-to-cart-sauce-labs-backpack";
    private String cartIcon =".shopping_cart_link"; //#shopping_cart_container

    public InventoryPage(Page page){
        this.page= page;
    }
    public void addProductToCart(){
        page.click(backpackAddButton);
    }
    public void goToCart(){
        page.click(cartIcon);
    }

    // Products browsing methods/ Implementations

    public void selectSortOption(String value){
        page.selectOption(sortContainer, value);
    }
    public List<String> getAllItemsNames(){
        return page.locator(itemNames).allInnerTexts();
    }
    public List<String> getAllItemsPrices(){
        return page.locator(itemPrices).allInnerTexts();
    }
    public void addItemToCart(String productName){
        page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName))
                .locator("button")
                .click();

    }
}
