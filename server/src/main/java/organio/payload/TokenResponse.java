package organio.payload;

public record TokenResponse(String token) {
    public static TokenResponse create(String token) {
        return new TokenResponse(token);
    }
}
