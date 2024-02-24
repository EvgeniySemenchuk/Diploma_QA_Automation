package pageObject.wildberries;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import pageObject.baseobject.BasePage;

public class Cookies extends BasePage {

    private final By acceptCookiesBtn = By.xpath("//*[@data-text=\"strAgree\"]");
    private final By denyCookiesBtn = By.xpath("//*[@data-text=\"strDeny\"]");

    public Cookies acceptCookies() {
        click(acceptCookiesBtn);
        return this;
    }

    public Cookies denyCookies() {
        click(denyCookiesBtn);
        return this;
    }
}
