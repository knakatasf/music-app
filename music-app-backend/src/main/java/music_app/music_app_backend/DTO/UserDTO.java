package music_app.music_app_backend.DTO;

import music_app.music_app_backend.entity.User;

public class UserDTO {
    private String userName;

    public UserDTO(String userName) {
        setUserName(userName);
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getUserName());
    }
}
