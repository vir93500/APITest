package responsepojo;


import lombok.Data;

public class UserCreationReponse {
    public Meta get_meta() {
        return _meta;
    }

    public void set_meta(Meta _meta) {
        this._meta = _meta;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private Meta _meta;
    private Result result;


}

