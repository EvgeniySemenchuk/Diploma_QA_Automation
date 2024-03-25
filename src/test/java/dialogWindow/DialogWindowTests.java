package dialogWindow;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.DialogWindow;
import pageObject.wildberries.Header;

public class DialogWindowTests extends BaseTest {

    @BeforeTest
    public void precondition() {
        get(Header.class).open();
        get(Cookies.class).denyCookies();
    }

    @Test(priority = 1, dataProvider = "text")
    public void sendingMessageTest(String message, Integer expectedSize) {
        get(DialogWindow.class).openChat()
                .verifyPage()
                .chooseChat(1)
                .sendMessage(message)
                .waitUntilPageLoaded();
        Assert.assertEquals(get(DialogWindow.class).sentMessagesSize(), expectedSize.toString(), "Wrong number of sent messages");
        Assert.assertEquals(get(DialogWindow.class).getTextMessage(expectedSize-1), message, "Sent text doesn't equal the one displayed in chat");
        get(DialogWindow.class).back().closeChat();
    }

    @DataProvider(name = "text")
    public Object[][] getData() {
        return new Object[][]{
                {"Привет", 1},
        };
    }


}
