package TestApiClass;

import Testbase.TestSetup;
import apihelper.CreateUserApiHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestpojo.UserCreationRequest;
import responsepojo.GenericResponse;
import responsepojo.GenericResponseDto;
import responsepojo.UserCreationReponse;

public class UserCreationApi extends TestSetup {
private UserCreationRequest userCreationRequest;
private CreateUserApiHelper createUserApiHelper;

    public UserCreationApi(){

    }

    @BeforeMethod
    public void beforemethod(){
        userCreationRequest = new UserCreationRequest();
        createUserApiHelper = new CreateUserApiHelper();
    }

    @Test
    public void postiveScenario(){
        userCreationRequest.setFirst_name("ABDerwre 1kohlwi234");
        userCreationRequest.setEmail("ABD1erwewr3w342@gmail.com");
        userCreationRequest.setGender("male");
        userCreationRequest.setLast_name("Kohli");
        userCreationRequest.setStatus("active");
        GenericResponseDto<UserCreationReponse> responseDto =  createUserApiHelper.userCreationReponseGenericResponseDto(userCreationRequest);
        System.out.println("response in testclass->>>>>"+responseDto);

        softAssert.assertEquals(responseDto.getData().get_meta().getMessage(),"A resource was successfully created in response to a POST request. The Location header contains the URL pointing to the newly created resource.");
        softAssert.assertEquals(responseDto.getData().getResult().getFirst_name(),"ABDerwre 1kohlwi24");
        softAssert.assertTrue(responseDto.getData().getResult().get_links().getSelf().getHref().contains("https://gorest.co.in/public-api/users/"));
       // softAssert.assertEquals(responseDto.get);


    }
}
