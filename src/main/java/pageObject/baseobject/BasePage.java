package pageObject.baseobject;

import io.cucumber.java.en_old.Ac;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static driver.DriverCreation.*;
import static java.io.File.separator;

@Log4j
public class BasePage {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected final String FILE_PATH = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "resources" + separator + "files" + separator;

    {
        driver = getWebDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected void navigateTo(String url) {
        log.info("Navigating to :: " + url);
        driver.get(url);
    }

    protected void click(WebElement element) {
        log.info("Click on - " + element);
        waitUntilElementToBeClickable(element);
        element.click();
    }

    protected void click(By by) {
        log.info("Click on - " + by);
        waitUntilElementToBeClickable(by);
        driver.findElement(by).click();
    }

    protected void click(String xpath) {
        click(By.xpath(xpath));
    }

    protected void doubleClick(WebElement element) {
        log.info("Double click on - " + element);
        waitUntilElementToBeClickable(element);
        actions.doubleClick(element);
    }

    protected void doubleClick(By by) {
        doubleClick(driver.findElement(by));
    }

    protected void doubleClick(String xpath) {
        doubleClick(By.xpath(xpath));
    }

    protected void scrollToElement(WebElement webElement) {
        log.info("Scrolling to element - " + webElement);
        actions.scrollToElement(webElement);
    }

    protected void scrollToElement(By by) {
        log.info("Scrolling to element - " + by);
        actions.scrollToElement(driver.findElement(by));
    }

    protected void scrollToElement(String xpath) {
        scrollToElement(By.xpath(xpath));
    }

    protected void enter(By by, CharSequence... charSequences) {
        enter(driver.findElement(by), charSequences);
    }

    protected void enter(WebElement element, CharSequence... charSequences) {
        log.info("Enter in :: " + element + ", next values :: " + Arrays.toString(charSequences));
        element.clear();
        sendKeys(element, charSequences);
    }

    protected void sendKeys(WebElement webElement, CharSequence... charSequences) {
        log.info("Enter in -" + webElement + " next values - " + Arrays.toString(charSequences));
        webElement.sendKeys(charSequences);
    }

    protected void sendKeys(By by, CharSequence... charSequences) {
        log.info("Enter in -" + by + " next values - " + Arrays.toString(charSequences));
        driver.findElement(by).sendKeys(charSequences);
    }

    protected void sendKeys(String xpath, CharSequence... charSequences) {
        sendKeys(By.xpath(xpath), charSequences);
    }

    protected Integer getElementsCount(By by) {
        return driver.findElements(by).size();
    }

    protected String getElementText(By by) {
        waitUntilElementBeVisible(by);
        return getElementText(driver.findElement(by));
    }

    protected String getElementText(WebElement webElement) {
        waitUntilElementBeVisible(webElement);
        return webElement.getText();
    }

    protected List<String> getElementTexts(By by) {
        return getElementTexts(driver.findElements(by));
    }

    protected List<String> getElementTexts(List<WebElement> webElements) {
        return webElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    protected void waitUntil(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitUntilTextToBe(By by, String expectedText) {
        log.info("Wait until text to be - " + expectedText);
        wait.until(ExpectedConditions.textToBe(by,expectedText));
    }

    protected void waitUntilTextNotToBe(By by, String expectedText) {
        log.info("Wait until text not to be - " + expectedText);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(by,expectedText)));
    }

    protected void waitUntilElementToBeClickable(WebElement webElement) {
        log.info("Wait until element to be clickable - " + webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitUntilElementToBeClickable(By by) {
        log.info("Wait until element to be clickable - " + by);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitUntilElementToBeClickable(String xpath) {
        waitUntilElementToBeClickable(By.xpath(xpath));
    }

    protected void waitUntilElementToBeNotClickable(By by) {
        log.info("Wait until element not to be clickable - " + by);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
    }

    protected void waitUntilElementToBeNotClickable(WebElement element) {
        log.info("Wait until element not to be clickable - " + element);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
    }

    protected void waitUntilElementToBeNotClickable(String xpath) {
        waitUntilElementToBeNotClickable(By.xpath(xpath));
    }

    protected void waitUntilElementBeVisible(By by) {
        log.info("Wait until element to visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitUntilElementBeVisible(WebElement webElement) {
        log.info("Wait until element to be visible - ");
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilElementBeVisible(String xpath) {
        log.info("Wait until element to be visible - ");
        waitUntilElementBeVisible(By.xpath(xpath));
    }

    protected void waitUntilAllElementsBeVisible(By by) {
        log.info("Wait until all elements to visible");
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(by)));
    }



}
