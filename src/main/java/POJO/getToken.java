package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class getToken {
    private String access_token;
    private int expires_in;
    private int refresh_expires_in;
    private String token_type;
    @JsonProperty("not-before-policy")
    private int not_before_policy;
    private String scope;
}
