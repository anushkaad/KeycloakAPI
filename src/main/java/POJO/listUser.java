package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class listUser {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
    private int createdTimestamp;
    private boolean enabled;
    private boolean totp;
    private Object[] disableableCredentialTypes;
    private Object[] requiredActions;
    private int notBefore;
    private Access access;
}
