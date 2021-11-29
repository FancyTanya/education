package ua.com.alevel.starteducation;

public class Routes {

    private Routes() {
        throw new AssertionError("non-instantiable class");
    }

    public static final String API_ROOT = "/api/v1";

    public static final String USERS = API_ROOT + "/users";

    public static final String STUDENTS = API_ROOT + "/students";

    public static final String TEACHERS = API_ROOT + "/teachers";

    public static final String TOKEN = API_ROOT + "/token";

    public static String user(long id) {
        return USERS + '/' + id;
    }

}
