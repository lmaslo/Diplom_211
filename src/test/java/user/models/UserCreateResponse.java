package user.models;

public class UserCreateResponse {
    private Boolean success;
    private String accessToken;
    private String refreshToken;
    private UserDataResponse user;

    public UserCreateResponse(Boolean success, String accessToken, String refreshToken, UserDataResponse user) {
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserDataResponse getUser() {
        return user;
    }

    public void setUser(UserDataResponse user) {
        this.user = user;
    }
}



