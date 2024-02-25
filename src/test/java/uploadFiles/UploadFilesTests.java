package uploadFiles;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

public class UploadFilesTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        get(Header.class).open().waitUntilPageLoaded();
    }

    @Test(dataProvider = "photo")
    public void uploadPhotoTest(String image, String result) {
        get(Header.class).uploadImage(image);
        get(SearchResultPage.class).waitUntilPageLoaded();
        Assert.assertTrue(get(SearchResultPage.class).verifySearch(result), "Uploaded image doesn't equal to search results");
    }

    @DataProvider(name = "photo")
    public Object[][] getData() {
        return new Object[][]{
                {"socks.png", "Носки"},
                {"pens.jpg", "Ручки"},
                {"jacket.jpg", "Куртка"},
        };
    }
}



