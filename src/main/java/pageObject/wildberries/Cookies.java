package pageObject.wildberries;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import pageObject.baseobject.BasePage;

public class Cookies extends BaseWBPage<Cookies> {

    private final By acceptCookiesBtn = By.xpath("//*[@data-text=\"strAgree\"]");
    private final By denyCookiesBtn = By.xpath("//*[@data-text=\"strDeny\"]");

    public Cookies acceptCookies() {
        click(acceptCookiesBtn);
        return me();
    }

    public Cookies denyCookies() {
        click(denyCookiesBtn);
        return me();
    }
}
