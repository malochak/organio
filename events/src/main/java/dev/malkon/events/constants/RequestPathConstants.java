package dev.malkon.events.constants;

import static dev.malkon.events.constants.Constants.UNAUTHORIZED_ACCESS_FORMAT;

public final class RequestPathConstants {

    public static final String API = "/api";
    public static final String ID_PATH = "/{id}";

    public static final String API_EVENT = API + "/event";

    private RequestPathConstants() {
        throw new AssertionError(String.format(UNAUTHORIZED_ACCESS_FORMAT, getClass().getName()));
    }
}
