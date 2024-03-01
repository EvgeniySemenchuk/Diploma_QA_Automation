package pageObject.wildberries;

import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class DialogWindow extends BaseWBPage<DialogWindow>{

    private final By chatBtn = By.xpath("//*[@data-tag=\"toggle\"]");
    private final By header = By.xpath("//*[@class=\"chat-header__title\"]");
    private final By searchField = By.xpath("//*[@data-tag=\"searchInput\"]");
    private final By chat = By.xpath("//*[@class=\"chat-list-item__in\"]");
    private final By messageArea = By.id("textarea");
    private final By sentMessage = By.xpath("//*[@class=\"chat-message  \"]");
    private final By closeBtn = By.xpath("//*[@class=\"chat-header__close\"] ");
    private final By closeFromChatBtn = By.xpath("//*[@data-tag=\"closeMin\"] ");
    private final By backBtn = By.xpath("//*[@class=\"chat-header__back\"] ");
    private final By messages = By.xpath("//*[@data-tag=\"messages\"]");

    public DialogWindow verifyPage() {
        waitUntilElementBeVisible(header);
        return me();
    }

    public DialogWindow verifyChat() {
        waitUntilElementBeVisible(messages);
        return me();
    }

    private String getChat(Integer index) {
        return "(//*[@class=\"chat-list-item__in\"])[" + index + "]";
    }

    public String getTextMessage(Integer index) {
        return (getElementText(driver.findElements(sentMessage).get(index)).split("\n"))[0];
    }

    public DialogWindow closeChat() {
        waitUntilElementToBeClickable(closeBtn);
        click(closeBtn);
        return me();
    }

    public DialogWindow back() {
        waitUntilElementToBeClickable(backBtn);
        click(backBtn);
        return me();
    }

    public DialogWindow closeFromChat() {
        waitUntilElementToBeClickable(closeFromChatBtn);
        click(closeFromChatBtn);
        return me();
    }

    public DialogWindow chooseChat(Integer index) {
        waitUntilElementToBeClickable(getChat(index));
        click(getChat(index));
        return me();
    }

    public DialogWindow openChat() {
        waitUntilElementToBeClickable(chatBtn);
        click(chatBtn);
        return me();
    }

    public DialogWindow search(String text) {
        enter(searchField,text);
        return me();
    }

    public DialogWindow sendMessage(String text) {
        enter(messageArea,text,Keys.ENTER);
        return me();
    }

    public String sentMessagesSize() {
        Integer size = driver.findElements(sentMessage).size();
        return size.toString();
    }

}
