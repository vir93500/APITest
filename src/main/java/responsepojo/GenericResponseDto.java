package responsepojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.Data;

import java.io.IOException;

/*
public class GenericResponseDto<T> {
    private T data;
    private ErrorResponse error;
    private ErrorResponseOther errorOther;

    public ErrorResponseOther getErrorOther() {
        return errorOther;
    }

    public void setErrorOther(ErrorResponseOther errorOther) {
        this.errorOther = errorOther;
    }

    private int statusCode;

    public T getData() {
        return this.data;
    }

    public ErrorResponse getError() {
        return this.error;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public synchronized GenericResponseDto<T> toClass(Response response, Class<T> class1) {
        ObjectMapper mapper = new ObjectMapper();
        if (null != response.jsonPath().get("errorMessage")) {
            this.error = mapper.convertValue(response.jsonPath().get(),
                    ErrorResponse.class);
        } else if (null != response.jsonPath().get("code") && null != response.jsonPath().get("message")) {
            this.errorOther = mapper.convertValue(response.jsonPath().get(),
                    ErrorResponseOther.class);
        } else
            this.data = response.as(class1);

        statusCode = response.getStatusCode();
        return this;
    }

    public synchronized GenericResponseDto<T> toClass(T response, Class<T> class1) {
        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            s = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode json = null;
        try {
            json = mapper.readTree(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != json.get("errorMessage")) {
            this.error = mapper.convertValue(json.get(0),
                    ErrorResponse.class);
        } else
            data = mapper.convertValue(json,
                    class1);

        return this;
    }

    public String toString() {
        return "GenericResponseDto(data=" + this.getData() + ", error=" + this.getError() + ")";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}*/


@Data
public class GenericResponseDto<T>{
    public T data;
    public ErrorResponse error;

    public GenericResponseDto<T> toClass(Response response,Class<T> class1){
        ObjectMapper objectMapper  =new ObjectMapper();
        if(response.jsonPath().get("errorMessage")!=null && response.jsonPath().get("errorCode")!=null){
            this.error = objectMapper.convertValue(response.jsonPath().get(),ErrorResponse.class);
        }
        else
            this.data = response.as(class1);
        return this;

    }

}