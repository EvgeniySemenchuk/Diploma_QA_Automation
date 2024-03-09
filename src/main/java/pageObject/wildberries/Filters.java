package pageObject.wildberries;

import entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Filters extends BaseWBPage<Filters> {

    private final By header = By.xpath("//span[@data-text=\"strFilters\"]");
    private final By priceFrom = By.xpath("//*[@data-tag=\"inputMin\"]");
    private final By priceTo = By.xpath("//*[@data-tag=\"inputMax\"]");
    private final By closeBtn = By.xpath("//*[@data-tag=\"sidebarClose\"]");
    private final By watchAllBrandsBtn = By.xpath("//*[@data-click-key=\"fbrand\"]//*[@data-tag=\"fold\"]");

    private String getBrand(Integer index) {
        return "(//*[@data-click-key=\"fbrand\"]//div[@class=\"filter__item \"])[" + index + "]";
    }

    private String getBrandByName(String productName) {
        return "//*[@data-click-key=\"fbrand\"]//span[contains(.,\""+ productName + "\")]//ancestor::*[@class=\"filter__item \"]";
    }

    public Filters selectBrand(Integer indexBrand) {
        click(getBrand(indexBrand));
        return me();
    }

    public Filters selectBrand(String productName) {
        click(getBrandByName(productName));
        return me();
    }

    public Filters selectBrand(Product product) {
        click(getBrandByName(product.getProductBrand()));
        return me();
    }

    public Filters watchAllBrands() {
        click(watchAllBrandsBtn);
        return me();
    }

    public Filters verifyPage() {
        waitUntilElementBeVisible(header);
        return me();
    }

    public Filters priceFrom(String price) {
        clearField(priceFrom);
        enter(priceFrom, price);
        return me();
    }

    public Filters priceTo(String price) {
        clearField(priceTo);
        enter(priceTo, price);
        return me();
    }

    public Filters close() {
        click(closeBtn);
        return me();
    }




}
