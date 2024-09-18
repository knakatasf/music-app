package music_app.music_app_backend.cotroller;

import music_app.music_app_backend.entity.Song;
import music_app.music_app_backend.repository.SongRepository;
import music_app.music_app_backend.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MusicAppController {
    private final LLMService llmService;
    private final SongRepository songRepository;

    @Autowired
    public MusicAppController(LLMService llmService, SongRepository songRepository) {
        this.llmService = llmService;
        this.songRepository = songRepository;
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


}
