package music_app.music_app_backend.cotroller;

import music_app.music_app_backend.DTO.SongDTO;
import music_app.music_app_backend.DTO.UserDTO;
import music_app.music_app_backend.entity.Song;
import music_app.music_app_backend.entity.User;
import music_app.music_app_backend.entity.UserFavorite;
import music_app.music_app_backend.repository.SongRepository;
import music_app.music_app_backend.repository.UserFavoriteRepository;
import music_app.music_app_backend.repository.UserRepository;
import music_app.music_app_backend.service.LLMService;
import music_app.music_app_backend.service.SongService;
import music_app.music_app_backend.service.UserFavoriteService;
import music_app.music_app_backend.service.UserService;
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
    private final SongService songService;
    private final UserService userService;
    private final UserFavoriteService userFavoriteService;

    @Autowired
    public MusicAppController(LLMService llmService, SongService songService, UserService userService, UserFavoriteService userFavoriteService) {
        this.llmService = llmService;
        this.songService = songService;
        this.userService = userService;
        this.userFavoriteService = userFavoriteService;
    }

    @GetMapping("")
    public void test() {
        String input = "Hikaru Utada";
        String recommendation = llmService.recommend(input);
        System.out.println(recommendation);
    }

    @GetMapping("/addSong")
    public String addSong() {
        SongDTO newSong = new SongDTO("Test Song", "David");
        songService.insertNewSong(newSong);
        return "Song added to db";
    }

    @GetMapping("/inputUserName")
    public UserDTO inputUsername(String userName) {
        UserDTO userDTO = userService.findUserByUserName(userName);
        return userDTO;
    }


}
