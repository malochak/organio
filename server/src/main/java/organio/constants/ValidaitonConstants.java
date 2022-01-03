package organio.constants;

import static organio.constants.Constants.UNAUTHORIZED_ACCESS_FORMAT;

public class ValidaitonConstants {
    public static final String NOT_BLANK = "cannot be blank.";
    public static final String NOT_VALID = "is not valid.";

    private ValidaitonConstants() {
        throw new AssertionError(String.format(UNAUTHORIZED_ACCESS_FORMAT, getClass().getName()));
    }
}
