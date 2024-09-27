package music_app.music_app_backend.Cotroller;

import music_app.music_app_backend.DTO.SongDTO;
import music_app.music_app_backend.Entity.AppUser;
import music_app.music_app_backend.Entity.Song;
import music_app.music_app_backend.Entity.UserFavorite;
import music_app.music_app_backend.Service.AppUserService;
import music_app.music_app_backend.Service.LLMService;
import music_app.music_app_backend.Service.SongService;
import music_app.music_app_backend.Service.UserFavoriteService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class RecommendationController {
    private final LLMService llmService;
    private final SongService songService;
    private final AppUserService appUserService;
    private final UserFavoriteService userFavoriteService;

    @Autowired
    public RecommendationController(LLMService llmService, SongService songService, AppUserService appUserService, UserFavoriteService userFavoriteService) {
        this.llmService = llmService;
        this.songService = songService;
        this.appUserService = appUserService;
        this.userFavoriteService = userFavoriteService;
    }


    @GetMapping("/recommend")
    public void test() {
        String input = "Hikaru Utada";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String)auth.getPrincipal();
        Optional<AppUser> currentUser = appUserService.findByUsername(userName);
        if (currentUser.isPresent()) {
            AppUser appUser = currentUser.get();
            Set<UserFavorite> favorites = appUser.getFavorites();
            List<SongDTO> favoriteSongs = new ArrayList<>();
            for (UserFavorite favorite : favorites) {
                favoriteSongs.add(SongDTO.fromEntity(favorite.getSong()));
            }
            llmService.recommend(input, favoriteSongs);
        }
        List<SongDTO> recommendations = llmService.recommend(input);
        for (SongDTO song : recommendations) {
            System.out.println(song);
        }
    }

    @GetMapping("/addSong")
    public String addSong() {
        SongDTO newSong = new SongDTO("Test Song", "David");
        songService.insertNewSong(newSong);
        return "Song added to db";
    }

//    @GetMapping("/inputUserName")
//    public void inputUsernameTest() {
//        String userName = "Koichi1212";
//        String input = "Ed Sheeran";
//        String password = "";
//        UserDTO userDTO = userService.findUserByUserName(userName, password);
//        List<SongDTO> favoriteSongs;
//        favoriteSongs = userFavoriteService.getUserFavoriteSongs(userDTO.getId());
//        List<SongDTO> recommendations;
//        if (favoriteSongs.isEmpty()) {
//            recommendations = llmService.recommend(input);
//        } else {
//            recommendations = llmService.recommend(input, favoriteSongs);
//        }
//        for (SongDTO song : recommendations) {
//            System.out.println(song);
//        }
//    }
//
//    @GetMapping("/inputUserNames/{userName}")
//    public ResponseEntity<String> inputUsernameTest2(@PathVariable String userName) {
//        String input = "Ed Sheeran"; // dao
//        UserDTO userDTO = userService.findUserByUserName(userName, "");
//        List<SongDTO> favoriteSongs;
//        favoriteSongs = userFavoriteService.getUserFavoriteSongs(userDTO.getId());
//        List<SongDTO> recommendations;
//        if (favoriteSongs.isEmpty()) {
//            recommendations = llmService.recommend(input);
//        } else {
//            recommendations = llmService.recommend(input, favoriteSongs);
//        }
//        for (SongDTO song : recommendations) {
//            System.out.println(song);
//        }
//
//        return ResponseEntity.ok(userName);
//    }


}
