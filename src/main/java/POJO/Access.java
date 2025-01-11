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
public class Access {

    private boolean manageGroupMembership;
    private boolean view;
    private boolean mapRoles;
    private boolean impersonate;
    private boolean manage;

}

