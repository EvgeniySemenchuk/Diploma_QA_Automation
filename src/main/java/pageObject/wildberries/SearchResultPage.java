package pageObject.wildberries;

import entities.Product;
import io.cucumber.java.eo.Do;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BaseWBPage<SearchResultPage> {

    private final By cardGrid = By.xpath("//*[@class=\"card-grid\"]");
    private final By searchResult = By.tagName("h1");
    private final By totalGoods = By.xpath("//div[@class=\"total-goods\"]");
    private final By backBtn = By.xpath("//a[@data-tag=\"goMain\"]");
    private final By scrollUpBtn = By.xpath("//*[contains(@class,\"scroll-top\")]");
    private final By productNames = By.xpath("//*[@class=\"product-card__name\"]");
    private final By productBrands = By.xpath("//*[@class=\"product-card__brand\"]");
    private final By notification = By.id("wbx-notification");
    private final By notFoundTitle = By.xpath("//*[@class=\"page-not-found__title\"]");
    private final By productPrices = By.xpath("//*[@data-tag=\"salePrice\"]");
    private final By filtersBtn = By.xpath("//button[@data-text=\"strFilters\"]");
    private final By totalUsedFilters = By.xpath("//span[@data-tag=\"totalDesktop\"]");

    private String getSwitcher(String switcher) {
        return "//*[@class=\"switcher\"]//button[contains(text(), \"" + switcher.toLowerCase() + "\")]";
    }

    public SearchResultPage openFilters() {
        click(filtersBtn);
        return me();
    }

    public SearchResultPage chooseSwitcher(String switcher) {
        click(getSwitcher(switcher));
        return me();
    }

    private List<WebElement> getProductPrices() {
        return driver.findElements(productPrices);
    }

    public List<String> getProductPricesData() {
        return getElementTexts(getProductPrices());
    }

    public List<Double> getProductPricesDouble() {
        List<Double> prices = new ArrayList<>();
        for (String price: getProductPricesData()) {
            prices.add(Double.parseDouble(price.replace(',','.')));
        }
        return prices;
    }

    public Boolean verifyPriceSwitcher() {
        boolean flag = true;
        List<Double> prices = getProductPricesDouble();
        for (int i = 0; i < prices.size()-1; i++) {
            if(prices.get(i) > prices.get(i+1)) {
                flag = false;
            }
        }
        return flag;
    }

    public String getNumberOfGoods() {
        return getElementText(totalGoods);
    }

    public SearchResultPage refresh() {
        refreshPage();
        return me();
    }

    public String getSearchResult() {
        return getElementText(searchResult);
    }

    public SearchResultPage verifyPage() {
        waitUntilElementBeVisible(cardGrid);
        waitUntilElementBeVisible(totalGoods);
        return this;
    }

    private String getProductCard(String productName, String index) {
        return "(//div[@class=\"product-snippet\"]//span[contains(. ,'" + productName + "')]/ancestor::div[@class=\"product-snippet\"])[" + index + "]";
    }

    private String getAddToBasket(String productName, String index) {
        return getProductCard(productName, index).concat("//*[@data-tag=\"basketBtn\"]");
    }

    private String getAddToFavorites(String productName, String index) {
        return getProductCard(productName,index).concat("//*[@data-tag=\"favouritesBtn\"]");
    }

    private String getAddToBasket(String index) {
        return "(//div[@class=\"product-snippet\"]//*[@data-tag=\"basketBtn\"])[" + index + "]";
    }

    private String getMoveToProduct(String productName, String index) {
        return getProductCard(productName, index).concat("//a[@class=\"product-card__link\"]");
    }

    private List<WebElement> getProductNames() {
        return driver.findElements(productNames);
    }

    private List<WebElement> getProductBrands() {
        return driver.findElements(productBrands);
    }

    public List<String> getProductBrandsData() { return getElementTexts(getProductBrands());}

    public List<String> getProductNamesData() {
        return getElementTexts(getProductNames());
    }

    public SearchResultPage addToFavorites(String productName, Integer productIndex) {
        click(getAddToFavorites(productName,productIndex.toString()));
        return me();
    }

    public SearchResultPage addToBasket(String productName, Integer productIndex) {
        waitUntilElementBeVisible(By.xpath(getAddToBasket(productName, productIndex.toString())));
        scrollToElement(getAddToBasket(productName, productIndex.toString()));
        click(getAddToBasket(productName, productIndex.toString()));
        return me();
    }

    public SearchResultPage addToBasket(Product product, Integer productIndex) {
        waitUntilElementBeVisible(By.xpath(getAddToBasket(product.getProductName(), productIndex.toString())));
        scrollToElement(getAddToBasket(product.getProductName(), productIndex.toString()));
        click(getAddToBasket(product.getProductName(), productIndex.toString()));
        return me();
    }

    public SearchResultPage addToBasket(Integer productIndex) {
        scrollToElement(getAddToBasket(productIndex.toString()));
        click(getAddToBasket(productIndex.toString()));
        return me();
    }

    public SearchResultPage moveToProduct(String productName, Integer productIndex) {
        click(getMoveToProduct(productName, productIndex.toString()));
        return me();
    }

    public SearchResultPage moveToProduct(Product product, Integer productIndex) {
        click(getMoveToProduct(product.getProductName(), productIndex.toString()));
        return me();
    }

    public SearchResultPage backToMainPage() {
        click(backBtn);
        return me();
    }

    public SearchResultPage scrollUp() {
        waitUntilElementToBeClickable(scrollUpBtn);
        click(scrollUpBtn);
        return me();
    }

    public Boolean verifyAnyNameSearch(String search) {
        return getProductNamesData().stream().anyMatch(el -> el.contains(search));
    }

    public Boolean verifyAllBrandSearch(String brand) {
        return getProductBrandsData().stream().allMatch(el->el.toLowerCase().contains(brand.toLowerCase()));
    }

    public Boolean notificationTextIsDisplayed() {
        return driver.findElement(notification).isDisplayed();
    }

    public Boolean notFoundTitleIsDisplayed() {
        return driver.findElement(notFoundTitle).isDisplayed();
    }

    public Boolean searchResultIsDisplayed() {
        return driver.findElement(searchResult).isDisplayed();
    }

    public String getTotalUsedFilters() {
        return getElementText(totalUsedFilters);
    }

}
