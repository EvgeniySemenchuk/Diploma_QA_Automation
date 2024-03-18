package apitests.post;

import api.pojos.Address;
import api.pojos.Product;
import api.specifications.Specification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiPostTests {

    private final static String IMAGE_DOWNLOAD_URL = "https://rec-filters.wildberries.ru";
    private final static String OFFICES_URL = "https://wbx-office-api.wildberries.ru";

    @Test()
    public void productPOSTImageSearchTests() {
        Specification.installSpecification(Specification.requestSpecMultiPart(IMAGE_DOWNLOAD_URL), Specification.responseSpecUnique(200));
        File uploadImage = new File("src/test/resources/files/pens.jpg");
        Response response = given()
                .baseUri(IMAGE_DOWNLOAD_URL)
                .basePath("/api/v1/recsAndFilters/imageSearch")
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
        Assert.assertNotNull(response);
        System.out.println(response.asString());
    }

    @Test()
    public void productPOSTDeliveryAddressesTests() {
        Specification.installSpecification(Specification.requestSpec(OFFICES_URL), Specification.responseSpecUnique(200));
        int[] array = new int[]{1828, 3588, 311409, 312351, 202762, 309142,
                311669, 50000389, 103980, 214699, 203001, 311011,
                105206, 211616, 172968, 136007, 215715, 203602,
                214380, 1913, 112115, 308698, 300586, 162133, 309506};
                Response response = given()
                .basePath("/api/v3/pvz")
                .body(array)
                .post();
        response.prettyPrint();
        List<Address> addresses = response.body().jsonPath().getList(".", Address.class);
        Assert.assertTrue(addresses.stream().anyMatch(x->x.getAddress().contains("Минск")));
        Assert.assertTrue(addresses.stream().allMatch(x->x.getLocale().contains("BY")));
        Assert.assertTrue(addresses.stream().allMatch(x->x.getIs_site_active().equals(true)));
        Assert.assertTrue(addresses.stream().noneMatch(x-> x.getWork_time().isEmpty()));
    }


    }
