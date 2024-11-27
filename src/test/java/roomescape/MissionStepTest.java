package roomescape;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import roomescape.api.dto.ReservationRequestDto;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SuppressWarnings("NonAsciiCharacters")
public class MissionStepTest {

    @Test
    void 일단계() {
        RestAssured.given().log().all()
                .when().get("/")
                .then().log().all()
                .statusCode(200);
    }

    @Nested
    class 이단계 {
        @Test
        void 예약_페이지_요청() {
            RestAssured.given().log().all()
                    .when().get("/reservation")
                    .then().log().all()
                    .statusCode(200);
        }

        @Test
        void 전체_예약_가져오기_요청() {
            RestAssured.given().log().all()
                    .when().get("/reservations")
                    .then().log().all()
                    .statusCode(200)
                    .body("size()", is(0));
        }
    }

    @Nested
    class 삼단계 {
        Map<String, String> requestBody = new HashMap<>();
//                "hwang",
//                "2024-11-06",
//                "10:11"


        @Test
        void 예약_추가_요청() {
            requestBody.put("name", "hwang");
            requestBody.put("date", "2024-11-06");
            requestBody.put("time_id", "1");
            requestBody.put("time", "10:11");


            RestAssured.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when().post("/reservations")
                    .then().log().all()
                    .statusCode(201)
                    .header("Location", "/reservations/1")
                    .body("id", is(1));

            RestAssured.given().log().all()
                    .when().get("/reservations")
                    .then().log().all()
                    .statusCode(200)
                    .body("size()", is(1));
        }

        @Test
        void 예약_삭제_요청() {
            RestAssured.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when().post("/reservations")
                    .then().log().all()
                    .statusCode(201)
                    .header("Location", "/reservations/1")
                    .body("id", is(1));

            RestAssured.given().log().all()
                    .when().delete("/reservations/1")
                    .then().log().all()
                    .statusCode(204);

            RestAssured.given().log().all()
                    .when().get("/reservations")
                    .then().log().all()
                    .statusCode(200)
                    .body("size()", is(0));
        }
    }

    @Nested
    class 사단계 {

        @Test
        void 예약_요청_인자_테스트() {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("name", "hwang");
            requestBody.put("date", "");
            requestBody.put("time", "");

            RestAssured.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when().post("/reservations")
                    .then().log().all()
                    .statusCode(400);
        }

        @Test
        void 없는_예약_삭제_테스트() {
            RestAssured.given().log().all()
                    .when().delete("/reservations/10")
                    .then().log().all()
                    .statusCode(400);
        }
    }
}
