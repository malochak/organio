package organio.constants;

public final class Constants {

    public static final String UNAUTHORIZED_ACCESS_FORMAT = "Unauthorized access to class constructor : %s";

    private Constants() {
        throw new AssertionError(String.format(UNAUTHORIZED_ACCESS_FORMAT, getClass().getName()));
    }
}
