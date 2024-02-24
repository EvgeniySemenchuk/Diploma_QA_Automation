package pageObject.wildberries;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObject.baseobject.BasePage;
import propertyUtils.PropertyReader;

public class Header extends BasePage {

    private final By searchField = By.xpath("//*[@data-text=\"strFindProducts\"]");
    private final By searchBtn = By.xpath("//button[@data-tag=\"searchBtn\"]");
    private final By searchImage = By.xpath("//*[@id=\"searchByPhoto\"]");
    private final By shoppingCard = By.xpath("(//a[@class=\"user-menu__btn\"])[4]");
    private final By navigationBtn = By.xpath("//button[@data-tag=\"catalogBtn\"]");
    private final By deliveryBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[1]");
    private final By favouritesBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[3]");

    public Header open() {
        navigateTo(PropertyReader.getProperties().getProperty("url"));
        return this;
    }

    public Header open(String url) {
        navigateTo(url);
        return this;
    }

    public Header enterSearch(String text) {
        sendKeys(searchField,text);
        return this;
    }

    public Header search(String searchText) {
        enter(searchField, searchText, Keys.ENTER);
        return this;
    }

    public Header moveToShoppingCard() {
        scrollToElement(shoppingCard);
        click(shoppingCard);
        return this;
    }

    public Header openNavigationBar() {
        click(navigationBtn);
        return this;
    }

    public Header uploadImage(String imageName) {
        sendKeys(searchImage, FILE_PATH.concat(imageName));
        return this;
    }


}
