package pageObject.wildberries;


import org.openqa.selenium.By;

public class FavoritesPage extends BaseWBPage<FavoritesPage> {

    private final By header = By.xpath("//*[@data-text=\"strFavourites\"]");
    private final By favoritesSize = By.xpath("//*[@class=\"favourites__body\"]//div[@class=\"product-snippet\"]");


    public FavoritesPage verifyPage() {
        waitUntilElementBeVisible(header);
        return this;
    }

    public String favoritesBasketSize() {
        return String.valueOf(driver.findElements(favoritesSize).size());
    }



}
