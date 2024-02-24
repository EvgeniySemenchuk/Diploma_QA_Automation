package pageObject.wildberries;

import io.cucumber.java.sl.In;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import pageObject.baseobject.BasePage;

public class PopUpPage extends BasePage {

    private final By closeBtn = By.xpath("//div[@aria-hidden=\"false\"]//button");


    public PopUpPage closePopUp(Integer index) {
        click(closeBtn);
        return this;
    }



}
