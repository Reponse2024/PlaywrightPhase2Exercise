package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    private String usernameInput = "#user-name";
    private String passwordInput = "#password";
    private String loginButton = "#login-button";
    private String menuButton = "#react-burger-menu-btn";
    private String logoutLink = "#logout_sidebar_link";

    public LoginPage(Page page){
        this.page = page;
    }

    public void login (String user, String pass){
        page.fill(usernameInput, user);
        page.fill(passwordInput, pass);
        page.click(loginButton);
    }
    public void logout() {
        page.click(menuButton);
        page.click(logoutLink);
    }
}
