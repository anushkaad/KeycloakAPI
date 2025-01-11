package helper;

public class APIEndPoints {
    public static final String CREATE_USER = "/admin/realms/master/users";
    public static final String GET_USER = "/admin/realms/master/users/%s";
    public static final String GET_TOKEN = "/realms/master/protocol/openid-connect/token";
    public static final String UPDATE_USER = "/admin/realms/master/users/%s";
    public static final String LIST_USER = "/admin/realms/master/users";
    public static final String COUNT_USER = "/admin/realms/master/users/count";
    public static final String DELETE_USER = "/admin/realms/master/users/%s";
    public static final String ENABLE_DISABLE_USER = "/admin/realms/master/users/%s";
}
