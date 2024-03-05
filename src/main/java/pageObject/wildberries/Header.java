package pageObject.wildberries;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObject.baseobject.BasePage;
import propertyUtils.PropertyReader;

public class Header extends BaseWBPage<Header> {

    private final By searchField = By.xpath("//*[@data-text=\"strFindProducts\"]");
    private final By searchBtn = By.xpath("//button[@data-tag=\"searchBtn\"]");
    private final By searchImage = By.xpath("//*[@id=\"searchByPhoto\"]");
    private final By shoppingCard = By.xpath("(//a[@class=\"user-menu__btn\"])[4]");
    private final By navigationBtn = By.xpath("//button[@data-tag=\"catalogBtn\"]");
    private final By deliveryBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[1]");
    private final By favouritesBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[3]");
    private final By entranceBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[3]");
    private final By itemsNumberInBasket = By.xpath("//*[@class=\"user-menu__badge\"]");

    public Header open() {
        navigateTo(PropertyReader.getProperties().getProperty("url"));
        return me();
    }

    public Header open(String url) {
        navigateTo(url);
        return me();
    }

    public Header enterSearch(String text) {
        sendKeys(searchField,text);
        return me();
    }

    public Header search(String searchText) {
        enter(searchField, searchText, Keys.ENTER);
        return me();
    }

    public Header entrance() {
        click(entranceBtn);
        return me();
    }

    public Header moveToShoppingCard() {
        scrollToElement(shoppingCard);
        click(shoppingCard);
        return me();
    }

    public Header openNavigationBar() {
        click(navigationBtn);
        return me();
    }

    public Header uploadImage(String imageName) {
        sendKeys(searchImage, FILE_PATH.concat(imageName));
        return me();
    }

    public String getNumberOfItemsInBasket() {
        return getElementText(itemsNumberInBasket);
    }


}
