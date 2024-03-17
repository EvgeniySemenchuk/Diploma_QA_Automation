package apitests.post;

import api.pojos.Product;
import api.specifications.Specification;
import io.qameta.allure.internal.shadowed.jackson.databind.util.JSONPObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.MultipartPartBuilder;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiPostTests {

    private final static String IMAGE_DOWNLOAD_URL = "https://rec-filters.wildberries.ru";
    private final static String SEARCH_URL = "https://a.wb.ru";
    private final static String BASKET_URL = "https://card.wb.ru";

    @Test()
    public void productPOSTTest() throws InterruptedException {
        File uploadImage = new File("src/test/resources/files/socks.png");
        Response response = given()
                .baseUri(IMAGE_DOWNLOAD_URL)
                .basePath("/api/v1/recsAndFilters/imageSearch")
                .contentType(ContentType.MULTIPART)
                .headers(new HashMap<>() {{
                    put("Accept", "*/*");
                    put("Accept-Encoding", "gzip, deflate, br");
                    put("Connection", "keep-alive");
                }})
                .params(new HashMap<>() {{
                    put("appType", "128");
                    put("curr", "byn");
                    put("lang", "ru");
                    put("dest", "-59208");
                    put("spp", "30");
                }})
                .multiPart("image", uploadImage)
                .post();
        response.prettyPrint();

    }

}
