package pageObject.wildberries;

import org.openqa.selenium.By;
import pageObject.baseobject.BasePage;

public class ProductPage extends BasePage {

    private final By addToCardBtn = By.xpath("(//*[@data-tag=\"basketButtonMain\"])[1]");
    private final By productPrice = By.xpath("(//*[@data-tag=\"productCurrentPrice\"])[1]");
    private final By favouritesBtn = By.xpath("(//*[@data-tag=\"favouritesButton\"])[1]");
    private final By articleNumber = By.xpath("//*[@data-tag=\"copyId\"]");

    public String getProductPrice() {
        waitUntilElementBeVisible(productPrice);
        return getElementText(productPrice);
    }

    public String getArticleNumber() {
        waitUntilElementBeVisible(articleNumber);
        return getElementText(articleNumber);
    }

    public ProductPage addToShoppingCard() {
        click(addToCardBtn);
        return this;
    }

    public ProductPage addToFavourites() {
        click(favouritesBtn);
        return this;
    }



}
