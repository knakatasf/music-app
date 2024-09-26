package music_app.music_app_backend.cotroller;

import music_app.music_app_backend.entity.AppUser;
import music_app.music_app_backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private AppUserRepository repository;

    @PostMapping(value = "/registration/signup", consumes = "application/json")
    public AppUser createUser(@RequestBody AppUser user) {
        System.out.println(user);
        return repository.save(user);
    }
}
