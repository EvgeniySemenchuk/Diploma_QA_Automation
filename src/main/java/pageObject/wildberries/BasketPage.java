package pageObject.wildberries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObject.baseobject.BasePage;

import java.util.List;

public class BasketPage extends BaseWBPage<BasketPage> {

    private final By productCount = By.xpath("//*[@data-tag=\"counter\"]");
    private final By totalPrice = By.xpath("//*[@data-tag=\"totalSum\"]");
    private final By basketSize = By.xpath("//div[@class=\"b-item\" or @class=\"b-item is-no-discount\"]");
    private final By prices = By.xpath("//div[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//div[@data-tag=\"salePrice\"]");
    private final By itemNames = By.xpath("//div[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"itemName\"]");

    private String getProductCard(String productName) {
        return "//div[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//span[contains(.,'" + productName + "')]";
    }

    private String getProductCard(String productName, String index) {
        return "(//div[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//span[contains(.,'" + productName + "')])[" + index + "]";
    }

    private String getDeleteProduct(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"deleteButton\"]");
    }

    private String getDeleteProduct(String productName, String index) {
        return getProductCard(productName, index).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"deleteButton\"]");
    }

    private String getProductPrice(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"salePrice\"]");
    }

    private String getProductPrice(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"salePrice\"]");
    }

    private String getQuantityMinus(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"quantityMinus\"]");
    }

    private String getQuantityMinus(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"quantityMinus\"]");
    }

    private String getQuantityPlus(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"quantityPlus\"]");
    }

    private String getQuantityPlus(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"quantityPlus\"]");
    }

    private String getAddToFavourites(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"favoriteButton\"]");
    }

    private String getAddToFavourites(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\" or @class=\"b-item is-no-discount\"]//*[@data-tag=\"favoriteButton\"]");
    }

    private List<WebElement> getPrices() {
        return driver.findElements(prices);
    }

    public List<String> getPricesData() {
        return getElementTexts(getPrices());
    }

    private List<WebElement> getNames() {
        return driver.findElements(itemNames);
    }

    public List<String> getProductNamesData() {
        return getElementTexts(getNames());
    }

    public String getProductCount() {
        return getElementText(productCount);
    }

    public String getTotalPrice() {
        return getElementText(totalPrice);
    }

    public Integer getBasketSize() {
        return driver.findElements(basketSize).size();
    }

    public BasketPage deleteProduct(String productName) {
        scrollToElement(getDeleteProduct(productName));
        click(getDeleteProduct(productName));
        return this;
    }

    public BasketPage deleteProduct(String productName, Integer index) {
        scrollToElement(getDeleteProduct(productName, index.toString()));
        click(getDeleteProduct(productName, index.toString()));
        return this;
    }

    public String getProductPriceData(String productName) {
        return getElementText(By.xpath(getProductPrice(productName)));
    }

    public String getProductPriceData(String productName, Integer index) {
        return getElementText(By.xpath(getProductPrice(productName, index.toString())));
    }

    public BasketPage plusProductQuantity(String productName) {
        click(getQuantityPlus(productName));
        return this;
    }

    public BasketPage plusProductQuantity(String productName, Integer index) {
        click(getQuantityPlus(productName, index.toString()));
        return this;
    }

    public BasketPage minusProductQuantity(String productName) {
        click(getQuantityMinus(productName));
        return this;
    }

    public BasketPage minusProductQuantity(String productName, Integer index) {
        click(getQuantityMinus(productName, index.toString()));
        return this;
    }

    public BasketPage addProductToFavourites(String productName) {
        click(getAddToFavourites(productName));
        return this;
    }

    public BasketPage addProductToFavourites(String productName, Integer index) {
        click(getAddToFavourites(productName,index.toString()));
        return this;
    }

}
