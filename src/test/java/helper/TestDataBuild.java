package helper;

import POJO.createUser;
import POJO.enabled;
import POJO.getUser;
import POJO.updateUser;

public class TestDataBuild {

    static testHelper helper = new testHelper();

    public static createUser createUserPayload(boolean isenabled) {
        return createUser.builder()
                .username(helper.getRandomFistName())
                .firstName(helper.getRandomFistName())
                .lastName(helper.getRandomLastName())
                .email(helper.getRandomEmail())
                .enabled(isenabled)
                .build();
    }

    public static updateUser updateUserPayload() {
        return updateUser.builder()
                .firstName(helper.getRandomFistName())
                .lastName(helper.getRandomLastName())
                .email(helper.getRandomEmail())
                .build();
    }

    public static enabled enabledOrDisablePayload(boolean isenabled) {
        return enabled.builder()
                .enabled(isenabled)
                .build();
    }


}
