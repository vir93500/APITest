package responsepojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class GenericResponse<C> {
    C response;
    Error error;

    public C getResponse() {
        return response;
    }

    public void setResponse(C data) {
        this.response = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public synchronized void toClass(Response response, Class<C> class1) {
        ObjectMapper mapper = new ObjectMapper();
        if (null != response.jsonPath().get("error")) {
            this.error = mapper.convertValue(response.jsonPath().get("error"),
                    Error.class);
        }
        if (null != response.jsonPath().get("data"))
            this.response = response.as(class1);
    }

    public String toString() {
        return String.format("GenericDto Response = (Data Part ="
                + getResponse() + " Error Part" + getError() + ")");
    }
}
