package organio.payload.authentication;

public interface AuthRequest {
    String getLogin();
    boolean errorOnExist();
    String getMessageOnExistError();
}
