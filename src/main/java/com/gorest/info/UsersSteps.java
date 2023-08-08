package com.gorest.info;

import com.gorest.constants.UsersEndPoints;
import com.gorest.model.UsersPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


/**
 * Created by Jay
 */


public class UsersSteps {

    static final String token = "Bearer 8632ea69c451b4442beb607e8b1e75784f5eac2b089d3fcce64c3946ab968840";

    @Step("Create the user with name : {0}, email : {1}, gender : {2}, status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {

        UsersPojo usersPojo = UsersPojo.getBooking(name, email, gender, status);


        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(usersPojo)
                .when()
                .post("/users")
                .then().log().all().statusCode(201);
    }

    @Step("Getting the users details with usersId : {0}")
    public ValidatableResponse getTheUsersDetails(int id) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .pathParam("id", id)
                .when()
                .get(UsersEndPoints.GET_SINGLE_USERS_BY_NAME)
                .then();

    }

    @Step("Updating the users details with usersId : {0},name : {1}, email : {2}, gender : {3}, status : {4} ")
    public ValidatableResponse updateTheUsersDetails(int id, String name, String email, String gender, String status) {

        UsersPojo usersPojo = UsersPojo.getBooking(name, email, gender, status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .pathParam("id", id)
                .body(usersPojo)
                .when()
                .put(UsersEndPoints.UPDATE_USERS_BY_ID)
                .then().log().all().statusCode(200);

    }

    @Step("Deleting the users with usersId : {0}")
    public ValidatableResponse deleteTheUser(int id) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .pathParam("id", id)
                .when()
                .delete(UsersEndPoints.DELETE_USERS_BY_ID)
                .then().log().all();

    }
}
