package roomescape.controller;


import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SuppressWarnings("NonAsciiCharacters")
public class HomeControllerTest {
    @Test
    void 홈_페이지_응답_테스트() {
        RestAssured.given().log().all()
                .when().get("/")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
