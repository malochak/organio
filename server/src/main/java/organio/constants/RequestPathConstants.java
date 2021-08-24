package organio.constants;

public final class RequestPathConstants {

    public static final String API = "/api";

    public static final String API_EVENT = API + "/event";

    private RequestPathConstants() {
        throw new AssertionError(String.format(Constants.UNAUTHORIZED_ACCESS_FORMAT, getClass().getName()));
    }
}
