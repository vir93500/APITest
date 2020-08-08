package TestApiClass;

import Testbase.TestSetup;
import apihelper.CreateUserApiHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.testng.annotations.Test;
import responsepojo.GenericResponseDto;
import responsepojo.UserLispResponse;

import java.sql.Date;
import java.util.Map;

public class UserListApi extends TestSetup {
    private CreateUserApiHelper createUserApiHelper;

    public UserListApi(){
        createUserApiHelper = new CreateUserApiHelper();
    }

    @Test
    public void testcase1(){
        GenericResponseDto<UserLispResponse> response  = createUserApiHelper.usersList();

        softAssert.assertTrue(response.getData().getResult().size()>0);
        System.out.println("response in testclass->>>>>"+response.getData().getResult().size());
    }


   /* private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }*/
}
