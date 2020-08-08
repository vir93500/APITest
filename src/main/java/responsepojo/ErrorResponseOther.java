package responsepojo;

public class ErrorResponseOther {

    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public ErrorResponseOther(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorResponseOther{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public ErrorResponseOther() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
