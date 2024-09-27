package music_app.music_app_backend.DTO;

import music_app.music_app_backend.entity.AppUser;

public class UserDTO {
    private Long id;
    private String userName;
    private String password;

    public UserDTO() {}

    public UserDTO(String userName) {
        this.userName = userName;
    }

    public UserDTO(Long id, String userName) {
        setId(id);
        setUserName(userName);
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() { return password; }

    public static UserDTO fromEntity(AppUser user) {
        return new UserDTO(user.getId(), user.getUserName());
    }
}
