package pageObject.wildberries;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.baseobject.BasePage;

public class BaseWBPage<P> extends BasePage {

    private final String preloader = "//*[@class='general-preloader']";

    public P waitUntilPageLoaded() {
        waitUntilPageLoaded(1);
        return me();
    }

    public P waitUntilPageLoaded(Integer timeout) {
        waitUntil(timeout);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath(preloader))));
        return me();
    }

    public P me() {
        return (P) this;
    }

}
