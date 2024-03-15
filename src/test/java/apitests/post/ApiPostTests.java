package apitests.post;

import api.pojos.Product;
import api.specifications.Specification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;
import pageObject.wildberries.BasketPage;
import pageObject.wildberries.Cookies;
import pageObject.wildberries.Header;
import pageObject.wildberries.SearchResultPage;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiPostTests extends BaseTest {

    private final static String IMAGE_DOWNLOAD_URL = "https://rec-filters.wildberries.ru";
    private final static String SEARCH_URL = "https://a.wb.ru";
    private final static String BASKET_URL = "https://card.wb.ru";

    @Test()
    public void productPOSTTest() {

    }

}
