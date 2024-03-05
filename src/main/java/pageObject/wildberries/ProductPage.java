package pageObject.wildberries;

import org.openqa.selenium.By;
import pageObject.baseobject.BasePage;

public class ProductPage extends BaseWBPage<ProductPage> {

    private final By addToCardBtn = By.xpath("(//*[@data-tag=\"basketButtonMain\"])[1]");
    private final By productPrice = By.xpath("(//*[@data-tag=\"productCurrentPrice\"])[1]");
    private final By favouritesBtn = By.xpath("(//*[@data-tag=\"favouritesButton\"])[1]");
    private final By articleNumber = By.xpath("//*[@data-tag=\"copyId\"]");
    private final By productName = By.xpath("//*[@data-tag=\"productName\"]");

    public String getProductPrice() {
        waitUntilElementBeVisible(productPrice);
        return getElementText(productPrice);
    }

    private String getProductSize(Integer index) {
        return "//*[@data-tag=\"sizesList\"]//*[@data-ind=\"" + (index-1) + "\"]";
    }

    public ProductPage chooseSize(Integer index) {
        waitUntilElementBeVisible(getProductSize(index));
        click(getProductSize(index));
        return me();
    }

    public String getProductName () {
        waitUntilElementBeVisible(productPrice);
        return getElementText(productName);
    }

    public String getArticleNumber() {
        waitUntilElementBeVisible(articleNumber);
        return getElementText(articleNumber);
    }

    public ProductPage addToShoppingCard() {
        click(addToCardBtn);
        return me();
    }

    public ProductPage addToFavourites() {
        click(favouritesBtn);
        return me();
    }



}
