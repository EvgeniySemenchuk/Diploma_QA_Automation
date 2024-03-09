package pageObject.wildberries;

import entities.Product;
import io.cucumber.java.sl.In;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObject.baseobject.BasePage;

import java.util.List;

public class PopUpPage extends BaseWBPage<PopUpPage> {

    private final By closeBtn = By.xpath("//div[@aria-hidden=\"false\"]//button");
    private final By sizes = By.xpath("//div[@aria-hidden=\"false\"]//*[@class=\"sizes-list__item\"]");

    private String getSizeByNumber(String size) {
        return "//*[contains(.,\"" + size + "\")]/ancestor::*[@class=\"sizes-list__item\"]";
    }

    private List<WebElement> getSizes() {
        return driver.findElements(sizes);
    }

    public List<String > getSizesData() {
        return getElementTexts(getSizes());
    }

    public PopUpPage closePopUp(Integer index) {
        click(closeBtn);
        return me();
    }

    public PopUpPage chooseSize(Integer index) {
        click(getSizes().get(index - 1));
        return me();
    }

    public PopUpPage chooseSize(String size) {
        click(getSizeByNumber(size));
        return me();
    }

    public PopUpPage chooseSize(Product product) {
        click(getSizeByNumber(product.getProductSize()));
        return me();
    }



}
