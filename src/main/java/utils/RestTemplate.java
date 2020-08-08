package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class RestTemplate {
public static Response post(final RequestSpecification request){
    return given().spec(request).when().log().all().post().then().extract().response();
}

public static Response get(RequestSpecification request){
    return given().spec(request).when().log().all().get().then().extract().response();
}
}
