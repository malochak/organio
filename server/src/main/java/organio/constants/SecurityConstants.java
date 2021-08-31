package organio.constants;

import static organio.constants.Constants.UNAUTHORIZED_ACCESS_FORMAT;

public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 60_000;
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_URL = "/api/auth/**";

    private SecurityConstants() {
        throw new AssertionError(String.format(UNAUTHORIZED_ACCESS_FORMAT, getClass().getName()));
    }
}

