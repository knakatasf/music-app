package music_app.music_app_backend.Service;

import music_app.music_app_backend.DTO.AppUserDTO;
import music_app.music_app_backend.Entity.AppUser;
import music_app.music_app_backend.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUserName(username);
        if (user.isPresent()) {
            AppUser appUser = user.get();
            return User.builder()
                    .username(appUser.getUserName())
                    .password(appUser.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public AppUserDTO createUser(AppUserDTO appUserDTO) {
        appUserRepository.save(new AppUser(
                appUserDTO.getUserName(),
                appUserDTO.getPassword()
        ));
        return appUserDTO;
    }

    public Optional<AppUser> findByUsername(String userName) {
        return appUserRepository.findByUserName(userName);
    }
}
