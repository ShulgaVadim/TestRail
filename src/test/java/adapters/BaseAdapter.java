package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.protocol.HTTP;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

@Log4j2
public class BaseAdapter {
    private static final String URL = "https://tmsshulga.testrail.io/index.php?/api/v2/";

    public Response post(File file, String request, int statusCode) {

        return given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .body(file)
                .when()
                .post(URL + request)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
    }

    public Response delete(String request, int statusCode) {

        return given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .when()
                .post(URL + request)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
    }


    public void isEdited(Response response, String field, String expected) {
        assertEquals(response.jsonPath().getString(field), expected);
    }

    public void isDeleted(String request, int statusCode, String errorMessage) {

        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .when()
                .get(URL + request)
                .then()
                .log().all()
                .statusCode(statusCode)
                .body("error", equalTo(errorMessage));
    }
}


