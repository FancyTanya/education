package ua.com.alevel.starteducation;

import java.util.UUID;

public class Routes {

    private Routes() {
        throw new AssertionError("non-instantiable class");
    }

    public static final String API_ROOT = "/api/v1";

    public static final String USERS = API_ROOT + "/users";

    public static final String FILES = API_ROOT + "/files";

    public static final String TOKEN = API_ROOT + "/token";

    public static String user(long id) {
        return USERS + '/' + id;
    }

    public static String file(UUID id) {
        return FILES + '/' + id;
    }
}
