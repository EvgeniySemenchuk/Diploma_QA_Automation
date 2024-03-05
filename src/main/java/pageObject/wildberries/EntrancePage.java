package pageObject.wildberries;

import io.cucumber.java.af.En;
import org.openqa.selenium.By;

public class EntrancePage extends BaseWBPage<EntrancePage> {

    private final By closeBtn = By.xpath("//button[@data-text=\"strClose\" and @class=\"modal-close\"]");
    private final By phoneInput = By.id("phone-input");
    private final By agreement = By.xpath("//*[@data-tag=\"checkbox\"]");
    private final By codeBtn = By.xpath("//*[@data-tag=\"nextBtn\"]");

    public EntrancePage enterNumber(String number) {
        sendKeys(phoneInput,number);
        return me();
    }

    public EntrancePage close() {
        click(closeBtn);
        return me();
    }

    public EntrancePage acceptAgreement() {
        click(agreement);
        return me();
    }

    public Boolean codeBtnIsEnabled() {
        return driver.findElement(codeBtn).isEnabled();
    }

    public EntrancePage clearInput() {
        driver.findElement(phoneInput).clear();
        return me();
    }

}
