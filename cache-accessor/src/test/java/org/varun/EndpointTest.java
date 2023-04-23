package org.varun;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.varun.entity.QuestionQuestion;
import org.varun.entity.QuestionQuestionKey;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class EndpointTest {

    public static final String PARENT_CODE = "ParentCode";
    public static final String CHILD_CODE = "ChildCode";
    public static final String ICON = "Icon";
    public static final String REALM = "Realm";
    public static final String CAPREQS = "Capreqs";

    @Test
    public void testHelloEndpoint() {
        given()
                .when()
                .get("/cache/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testGetEndpoint() {
        given()
                .when()
                .get("/cache/getquestionquestion/realm,sourcecode,targetcode")
                .then()
                .statusCode(200)
                .body(is(Constants.QUESTIONQUESTION_NOT_FOUND_IN_CACHE));
    }

    @Test
    public void testPostEndpoint() {
        QuestionQuestion questionQuestion = buildRandomQuestionQuestion();
        QuestionQuestionKey key = new QuestionQuestionKey(questionQuestion.getRealm(), questionQuestion.getSourceCode(), questionQuestion.getTargetCode());
        given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Entity.json(questionQuestion))
                .post("/cache/postquestionquestion")
                .then()
                .statusCode(200)
                .body(is(key));
    }

    private static QuestionQuestion buildRandomQuestionQuestion() {
        QuestionQuestion questionQuestion = new QuestionQuestion();
        double randomValue = Math.random();
        questionQuestion.setSourceCode(PARENT_CODE + randomValue);
        questionQuestion.setTargetCode(CHILD_CODE + randomValue);
        questionQuestion.setCreated(LocalDateTime.now());
        questionQuestion.setDisabled(false);
        questionQuestion.setHidden(false);
        questionQuestion.setIcon(ICON + randomValue);
        questionQuestion.setMandatory(false);
        questionQuestion.setReadonly(false);
        questionQuestion.setRealm(REALM + randomValue);
        questionQuestion.setUpdated(LocalDateTime.now());
        questionQuestion.setVersion(1L);
        questionQuestion.setWeight(1D);
        questionQuestion.setCapreqs(CAPREQS + randomValue);
        return questionQuestion;
    }
}