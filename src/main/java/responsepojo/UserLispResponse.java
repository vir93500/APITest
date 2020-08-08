package responsepojo;

import lombok.Data;

import java.util.List;

@Data
public class UserLispResponse {

    private Meta _meta;
    private List<Result> result;

    @Data
    static class Meta{
        public String success;
        public int code;
                public String message;
                public int totalCount;
                public int pageCount;
                public int currentPage;
                public int perPage;
                public RateLimit rateLimit;

                @Data
                static class RateLimit{
                    public int limit;
                    public int remaining;
                    public int reset ;
                }

    }

}
