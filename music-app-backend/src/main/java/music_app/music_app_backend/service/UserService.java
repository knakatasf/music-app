package music_app.music_app_backend.service;

import music_app.music_app_backend.entity.User;
import music_app.music_app_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insertUser(User user) {
        userRepository.save(user);
        return user;
    }

}
