package pageObject.wildberries;

import io.cucumber.java.eo.Se;
import io.cucumber.java.ja.然し;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObject.baseobject.BasePage;

import java.util.List;

public class SearchResultPage extends BaseWBPage<SearchResultPage> {

    private final By searchResult = By.tagName("h1");
    private final By totalGoods = By.xpath("//div[@class=\"total-goods\"]");
    private final By backBtn = By.xpath("//a[@data-tag=\"goMain\"]");
    private final By scrollUpBtn = By.xpath("//*[contains(@class,\"scroll-top\")]");
    private final By productNames = By.xpath("//*[@class=\"product-card__name\"]");
    private final By switchers = By.xpath("//*[@class=\"switcher\"]//button");

    public String getNumberOfGoods() {
        return getElementText(totalGoods);
    }

    public String getSearchResult() {
        return getElementText(searchResult);
    }

    private String getProductCard(String productName, String index) {
        return "(//div[@class=\"product-snippet\"]//span[contains(. ,'" + productName + "')]/ancestor::div[@class=\"product-snippet\"])["+index+"]";
    }

    private String getAddToBasket(String productName, String index) {
        return getProductCard(productName, index).concat("//*[@data-tag=\"basketBtn\"]");
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

    public List<String> getSwitchersData() {
        return getElementTexts(switchers);
    }

    public List<String> getProductNamesData() {
        return getElementTexts(getProductNames());
    }

    public SearchResultPage addToBasket(String productName, Integer productIndex) {
        waitUntilElementBeVisible(By.xpath(getAddToBasket(productName,productIndex.toString())));
        scrollToElement(getAddToBasket(productName, productIndex.toString()));
        click(getAddToBasket(productName, productIndex.toString()));
        return this;
    }

    public SearchResultPage addToBasket(Integer productIndex) {
        scrollToElement(getAddToBasket(productIndex.toString()));
        click(getAddToBasket(productIndex.toString()));
        return this;
    }

    public SearchResultPage moveToProduct(String productName, Integer productIndex) {
        click(getMoveToProduct(productName, productIndex.toString()));
        return this;
    }

    public SearchResultPage backToMainPage() {
        click(backBtn);
        return this;
    }

    public SearchResultPage scrollUp() {
        waitUntilElementToBeClickable(scrollUpBtn);
        click(scrollUpBtn);
        return this;
    }

    public Boolean verifySearch(String search) {
        return getProductNamesData().stream().anyMatch(el->el.contains(search));
    }



}
