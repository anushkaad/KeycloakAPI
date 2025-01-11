package POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class createUser {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
}
