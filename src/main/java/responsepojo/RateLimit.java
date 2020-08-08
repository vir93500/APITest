package responsepojo;

import lombok.Data;

@Data
 public class RateLimit {
    public int limit;
    public int remaining;
    public int reset ;

}