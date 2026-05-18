package pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {
  private final Page page;

  public CheckoutPage(Page page){
      this.page = page;
  }

  public void proceedToCheckout(){

      page.click("#checkout");
  }
  public void fillInformation(String first, String last, String zip){
      page.fill ("#first-name", first);
      page.fill ("#last-name", last);
      page.fill("#postal-code", zip);
      page.click("#continue");
  }
  public void finishCheckout(){
      page.click("#finish");
  }
  public String getSuccessMessage(){

      return page.innerText(".complete-header");
  }
  public void gobackHome(){
      page.click("#back-to-products");
  }
}
