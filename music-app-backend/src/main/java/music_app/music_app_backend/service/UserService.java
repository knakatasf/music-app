package music_app.music_app_backend.service;

import music_app.music_app_backend.DTO.UserDTO;
import music_app.music_app_backend.entity.User;
import music_app.music_app_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findUserByUserName(String userName) {
        UserDTO userDTO;
        if (userRepository.existsByUserName(userName)) {
            User foundUser = userRepository.findByUserName(userName);
            userDTO = UserDTO.fromEntity(foundUser);
        } else {
            userDTO = insertNewUser(userName);
        }
        return userDTO;
    }

    private UserDTO insertNewUser(String userName) {
        User savedUser = userRepository.save(new User(userName));
        return UserDTO.fromEntity(savedUser);
    }

}
