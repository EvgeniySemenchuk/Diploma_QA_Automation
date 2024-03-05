package pageObject.wildberries;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Filters extends BaseWBPage<Filters> {

    private final By header = By.xpath("//span[@data-text=\"strFilters\"]");
    private final By priceFrom = By.xpath("//*[@data-tag=\"inputMin\"]");
    private final By priceTo = By.xpath("//*[@data-tag=\"inputMax\"]");
    private final By closeBtn = By.xpath("//*[@data-tag=\"sidebarClose\"]");

    public Filters verifyPage() {
        waitUntilElementBeVisible(header);
        return me();
    }

    public Filters priceFrom(String price) {
        enter(priceFrom, price, Keys.ENTER);
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
