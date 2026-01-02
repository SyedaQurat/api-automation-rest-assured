import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Feature("Products API")
public class FakeAPITest {

    int id = 101;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/";

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("GET all products returns status 200")
    public void getProducts() {
        RestAssured.given()
                .log().all()
                .when().get(BASE_URL).then().statusCode(200);
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("GET specific product with ID returns status 200")
    public void getProductsWithId() {
        RestAssured.given()
                .log().all()
                .when().get(BASE_URL + id).then().statusCode(200);
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("ADD product returns status 200")
    public void addProducts() {
        Products product = Products.builder()
                .id(101)
                .title("Shirt")
                .body("bar")
                .userId(1)
                .build();

        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (GitHub Actions)")
                .body(product)
                .when().post(BASE_URL).then().statusCode(201);
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("ADD product with Incorrect URL returns status 404")
    public void addProductsWith404() {
        Products product = Products.builder()
                .id(101)
                .title("Shirt")
                .body("bar")
                .userId(1)
                .build();

        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(product)
                .when().post("https://fakestoreapi.com/product").then().statusCode(404);
    }

//    @Test(groups = {"smoke", "regression"})
//    @Severity(SeverityLevel.CRITICAL)
//    @Description("UPDATE product returns status 200")
//    public void updateProducts() {
//        Products product = Products.builder()
//                .title("Shirts")
//                .build();
//
//        RestAssured.given()
//                .log().all()
//                .header("Content-Type", "application/json")
//                .body(product)
//                .when().put(BASE_URL + "404").then().statusCode(200);
//    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("UPDATE product with Incorrect URL returns status 404")
    public void updateProductsWith404() {
        Products product = Products.builder()
                .title("Shirts")
                .build();

        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(product)
                .when().put("https://fakestoreapi.com/product" + id).then().statusCode(404);
    }

    @Test(groups = {"smoke", "regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("DELETE specific product with ID returns status 200")
    public void deleteProduct() {
        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .when().delete(BASE_URL + id).then().statusCode(200);
    }

    @Test(groups = {"regression"})
    @Severity(SeverityLevel.NORMAL)
    @Description("DELETE product with Incorrect URL returns status 404")
    public void deleteProductWith404() {
        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .when().delete("https://fakestoreapi.com/product").then().statusCode(404);
    }
}
