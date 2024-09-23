package music_app.music_app_backend.service;

import music_app.music_app_backend.DTO.SongDTO;
import music_app.music_app_backend.entity.Song;
import org.springframework.stereotype.Service;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.*;

import java.util.List;
import java.util.Optional;

@Service
public class LLMService {
    private final ChatLanguageModel chatLanguageModel;

    public LLMService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    interface AiRecommender {
        @UserMessage("Recommend three songs based on the user's following favorites: {{it}}")
        String recommendBasedOnInput(String input);

        @UserMessage("Recommend three songs based on the user's following favorites:" +
                "{{it}} and {{favoriteSongs}}")
        String recommendBasedOnInputAndFavorites(String input, List<SongDTO> favoriteSongs);
    }

    public String recommend(String input) {
        AiRecommender aiRecommender = AiServices.create(AiRecommender.class, chatLanguageModel);
        String recommendations = aiRecommender.recommendBasedOnInput(input);
        return recommendations;
    }

    public String recommend(String input, List<SongDTO> favoriteSongs) {
        AiRecommender aiRecommender = AiServices.create(AiRecommender.class, chatLanguageModel);
        String recommendations = aiRecommender.recommendBasedOnInputAndFavorites(input, favoriteSongs);
        return recommendations;
    }


}

