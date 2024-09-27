package music_app.music_app_backend.Service;

import music_app.music_app_backend.DTO.SongDTO;
import org.springframework.stereotype.Service;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class LLMService {
    private final ChatLanguageModel chatLanguageModel;

    public LLMService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    interface AiRecommender {
        @UserMessage("Recommend three songs based on the user's following favorites: {{it}}." +
                "The response should be formatted as:\n" +
                "Song Name by Artist, Song Name by Artist, Song Name by Artist")
        String recommendBasedOnInput(String input);

        @UserMessage("Recommend three songs based on the user's following favorites: {{it}} and {{favoriteSongs}}." +
                "The response should be formatted as:\n" +
                "Song Name by Artist, Song Name by Artist, Song Name by Artist")
        String recommendBasedOnInputAndFavorites(String input, List<SongDTO> favoriteSongs);
    }

    public List<SongDTO> recommend(String input) {
        AiRecommender aiRecommender = AiServices.create(AiRecommender.class, chatLanguageModel);
        String recommendations = aiRecommender.recommendBasedOnInput(input);
        String[] li = recommendations.split(",");

        List<SongDTO> songs = new ArrayList<>();
        for (String songByArtist : li) {
            songs.add(SongDTO.fromString(songByArtist.trim()));
        }
        return songs;
    }


    public List<SongDTO> recommend(String input, List<SongDTO> favoriteSongs) {
        AiRecommender aiRecommender = AiServices.create(AiRecommender.class, chatLanguageModel);
        String recommendations = aiRecommender.recommendBasedOnInputAndFavorites(input, favoriteSongs);

        String[] li = recommendations.split(",");

        List<SongDTO> songs = new ArrayList<>();
        for (String songByArtist : li) {
            songs.add(SongDTO.fromString(songByArtist.trim()));
        }
        return songs;
    }
}

