package pageObject.wildberries;

import entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import propertyUtils.PropertyReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Header extends BaseWBPage<Header> {

    private final By searchField = By.xpath("//*[@data-text=\"strFindProducts\"]");
    private final By searchBtn = By.xpath("//button[@data-tag=\"searchBtn\"]");
    private final By searchImage = By.xpath("//*[@id=\"searchByPhoto\"]");
    private final By shoppingCard = By.xpath("(//a[@class=\"user-menu__btn\"])[4]");
    private final By navigationBtn = By.xpath("//button[@data-tag=\"catalogBtn\"]");
    private final By deliveryBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[1]");
    private final By favouritesBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[2]");
    private final By entranceBtn = By.xpath("(//a[@class=\"user-menu__btn\"])[3]");
    private final By itemsNumberInBasket = By.xpath("//*[@class=\"user-menu__badge\"]");
    private final By backBtn = By.xpath("//a[@data-tag=\"goMain\"]");

    public Header open() {
        navigateTo(PropertyReader.getProperties().getProperty("url"));
        return me();
    }

    public Header open(String url) {
        navigateTo(url);
        return me();
    }

    public Header enterSearch(String text) {
        sendKeys(searchField, text);
        return me();
    }

    public Header search(String searchText) {
        enter(searchField, searchText, Keys.ENTER);
        return me();
    }

    public Header search(Product product) {
        enter(searchField, product.getProductName(), Keys.ENTER);
        return me();
    }

    public Header searchByFile(String filepath) {
        enter(searchField, getFileData(filepath), Keys.ENTER);
        return me();
    }

    public String getFileData(String filePath) {
        File file = new File(filePath);
        StringBuilder strBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                strBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuilder.toString();
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

    public Header backToMainPage() {
        click(backBtn);
        return  me();
    }

    public Header moveToFavorites() {
        click(favouritesBtn);
        return me();
    }
}
