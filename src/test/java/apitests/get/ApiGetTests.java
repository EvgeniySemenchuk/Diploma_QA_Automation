package apitests.get;

import api.pojos.Product;
import api.specifications.Specification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.baseobject.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiGetTests {

    private static final String SEARCH_TAGS_URL = "https://similar-queries.wildberries.ru";
    private static final String SEARCH_WB = "https://search.wb.ru";

    @Test(priority = 1)
    public void productGETTest() {
        Specification.installSpecification(Specification.requestSpec(SEARCH_WB), Specification.responseSpecUnique(200));
        Response response = given()
                .basePath("/exactmatch/sng/common/v4/search")
                .params(new HashMap<>() {{
                    put("query", "матрас");
                    put("resultset", "catalog");
                    put("limit", "100");
                    put("sort", "popular");
                    put("dest", "-59208");
                }}).get();
        List<Product> products = response.body().jsonPath().getList("data.products.", Product.class);
        products.forEach(x -> Assert.assertTrue(x.getName().toLowerCase().contains("матр")));
        Assert.assertEquals(products.size(), 100);
    }

    @Test(priority = 2)
    public void priceSortGETTest() {
        Specification.installSpecification(Specification.requestSpec(SEARCH_WB), Specification.responseSpecUnique(200));
        Response response = given()
                .basePath("/exactmatch/sng/common/v4/search")
                .params(new HashMap<>() {{
                    put("query", "матрас");
                    put("resultset", "catalog");
                    put("limit", "100");
                    put("sort", "priceup");
                    put("dest", "-59208");
                }}).get();
        List<Product> products = response.body().jsonPath().getList("data.products.", Product.class);
        List<Integer> prices = products.stream().map(Product::getSalePriceU).collect(Collectors.toList());
        List<Integer> sortedPrices = prices.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(prices, sortedPrices);
    }

    @Test(priority = 3)
    public void searchTagsGETTest() {
        Specification.installSpecification(Specification.requestSpec(SEARCH_TAGS_URL), Specification.responseSpecUnique(200));
        Response response = given()
                .basePath("/api/v2/search/query")
                .params(new HashMap<>() {{
                    put("query", "книга");
                }}).get();
        List<String> searchTags = response.body().jsonPath().getList("query");
        Assert.assertNotNull(searchTags);
        Assert.assertTrue(searchTags.stream().anyMatch(x->x.contains("книг")));
    }

}
