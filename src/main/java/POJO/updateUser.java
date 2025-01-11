package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import com.fasterxml.jackson.annotation.JsonProperty;


@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class updateUser {
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
}
