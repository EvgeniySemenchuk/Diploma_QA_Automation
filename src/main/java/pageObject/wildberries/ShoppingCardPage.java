package pageObject.wildberries;

import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObject.baseobject.BasePage;

import java.util.List;

public class ShoppingCardPage extends BasePage {

    private final By productCount = By.xpath("//*[@data-tag=\"counter\"]");
    private final By totalPrice = By.xpath("//*[@data-tag=\"totalSum\"]");
    private final By prices = By.xpath("//div[@data-tag=\"salePrice\"]");

    private String getProductCard(String productName) {
        return "//*[@class=\"b-item\"]//span[contains(.,'" + productName + "')]";
    }

    private String getProductCard(String productName, String index) {
        return "(//*[@class=\"b-item\"]//span[contains(.,'" + productName + "')])[" + index + "]";
    }

    private String getDeleteProduct(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"deleteButton\"]");
    }

    private String getDeleteProduct(String productName, String index) {
        return getProductCard(productName, index).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"deleteButton\"]");
    }

    private String getProductPrice(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"salePrice\"]");
    }

    private String getProductPrice(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"salePrice\"]");
    }

    private String getQuantityMinus(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"quantityMinus\"]");
    }

    private String getQuantityMinus(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"quantityMinus\"]");
    }

    private String getQuantityPlus(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"quantityPlus\"]");
    }

    private String getQuantityPlus(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"quantityPlus\"]");
    }

    private String getAddToFavourites(String productName, String index) {
        return getProductCard(productName,index).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"favoriteButton\"]");
    }

    private String getAddToFavourites(String productName) {
        return getProductCard(productName).concat("//ancestor::*[@class=\"b-item\"]//*[@data-tag=\"favoriteButton\"]");
    }

    private List<WebElement> getPrices() {
        return driver.findElements(prices);
    }

    public List<String> getPricesData() {
        return getElementTexts(getPrices());
    }

    public String getProductCount() {
        return getElementText(productCount);
    }

    public String getTotalPrice() {
        return getElementText(totalPrice);
    }

    public ShoppingCardPage deleteProduct(String productName) {
        click(getDeleteProduct(productName));
        return this;
    }

    public ShoppingCardPage deleteProduct(String productName, Integer index) {
        click(getDeleteProduct(productName, index.toString()));
        return this;
    }

    public String getProductPriceData(String productName) {
        return getElementText(By.xpath(getProductPrice(productName)));
    }

    public String getProductPriceData(String productName, Integer index) {
        return getElementText(By.xpath(getProductPrice(productName, index.toString())));
    }

    public ShoppingCardPage plusProductQuantity(String productName) {
        click(getQuantityPlus(productName));
        return this;
    }

    public ShoppingCardPage plusProductQuantity(String productName, Integer index) {
        click(getQuantityPlus(productName, index.toString()));
        return this;
    }

    public ShoppingCardPage minusProductQuantity(String productName) {
        click(getQuantityMinus(productName));
        return this;
    }

    public ShoppingCardPage minusProductQuantity(String productName, Integer index) {
        click(getQuantityMinus(productName, index.toString()));
        return this;
    }

    public ShoppingCardPage addProductToFavourites(String productName) {
        click(getAddToFavourites(productName));
        return this;
    }

    public ShoppingCardPage addProductToFavourites(String productName, Integer index) {
        click(getAddToFavourites(productName,index.toString()));
        return this;
    }

}
