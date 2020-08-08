package apihelper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import requestpojo.UserCreationRequest;
import responsepojo.GenericResponseDto;
import responsepojo.UserCreationReponse;
import responsepojo.UserLispResponse;
import utils.RestTemplate;


public class CreateUserApiHelper {
    private RequestSpecification requestSpecification;
    private String baseUri = "https://gorest.co.in";
    private GenericResponseDto genericResponse = new GenericResponseDto();
    private static Logger log = Logger.getLogger(CreateUserApiHelper.class);

    public CreateUserApiHelper(){
        RestAssured.baseURI = baseUri;
    }
    public GenericResponseDto<UserCreationReponse> userCreationReponseGenericResponseDto(UserCreationRequest request){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath("/public-api/users");
        requestSpecBuilder.setBody(request);
        requestSpecBuilder.setContentType(io.restassured.http.ContentType.JSON);
		requestSpecBuilder.addHeader("Authorization", "Bearer dMgNM7IS-dJ_G0ov_vIfwkWn742jYQWSF7P4");
		requestSpecification = requestSpecBuilder.build();

        log.info("requestBody----------->"+requestSpecBuilder.toString());
        Response response = RestTemplate.post(requestSpecification);
        log.info("response body----------->"+response.asString());
       // genericResponse.toClass(response.asString(),UserCreationReponse.class);
        return genericResponse.toClass(response,UserCreationReponse.class);
    }

    public  GenericResponseDto<UserLispResponse> usersList(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath("/public-api/users");
        requestSpecBuilder.setContentType(io.restassured.http.ContentType.JSON);
        requestSpecBuilder.addHeader("Authorization", "Bearer dMgNM7IS-dJ_G0ov_vIfwkWn742jYQWSF7P4");
        requestSpecBuilder.addHeader("access-token","dMgNM7IS-dJ_G0ov_vIfwkWn742jYQWSF7P4");
        requestSpecification = requestSpecBuilder.build();
        log.info("requestBody----------->"+requestSpecBuilder.toString());
        Response response = RestTemplate.get(requestSpecification);
        log.info("response body----------->"+response.asString());
        return genericResponse.toClass(response,UserLispResponse.class);
    }

}
