package music_app.music_app_backend.cotroller;

import music_app.music_app_backend.entity.Song;
import music_app.music_app_backend.entity.User;
import music_app.music_app_backend.entity.UserFavorite;
import music_app.music_app_backend.repository.SongRepository;
import music_app.music_app_backend.repository.UserFavoriteRepository;
import music_app.music_app_backend.repository.UserRepository;
import music_app.music_app_backend.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MusicAppController {
    private final LLMService llmService;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final UserFavoriteRepository userFavoriteRepository;

    @Autowired
    public MusicAppController(LLMService llmService, SongRepository songRepository, UserRepository userRepository, UserFavoriteRepository userFavoriteRepository) {
        this.llmService = llmService;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.userFavoriteRepository = userFavoriteRepository;
    }

    @GetMapping("")
    public void test() {
        String input = "Hikaru Utada";
        String recommendation = llmService.recommend(input);
        System.out.println(recommendation);
    }

    @GetMapping("/addSong")
    public String addSong() {
        Song newSong = new Song("Test Song", "David");
        songRepository.save(newSong);
        return "Song added to db";
    }

    @GetMapping("/inputUserName")
    public User loginByUsername(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseGet(() -> new User(userName));

        List<Song> favorites = userFavoriteRepository.findFavoriteSongsByUserId(user.getId());

        return user;
    }


}
