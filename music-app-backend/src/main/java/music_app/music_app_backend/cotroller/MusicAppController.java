package music_app.music_app_backend.cotroller;

import music_app.music_app_backend.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MusicAppController {
    private final LLMService llmService;

    @Autowired
    public MusicAppController(LLMService llmService) {
        this.llmService = llmService;
    }

    @GetMapping("")
    public void test() {
        String input = "Hikaru Utada";
        String recommendation = llmService.recommend(input);
        System.out.println(recommendation);
    }
}
