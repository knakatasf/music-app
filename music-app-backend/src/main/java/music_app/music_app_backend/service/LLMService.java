package music_app.music_app_backend.service;

import org.springframework.stereotype.Service;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.*;

@Service
public class LLMService {
    private final ChatLanguageModel chatLanguageModel;

    public LLMService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    interface AiRecommender {
        @UserMessage("Recommend three songs based on the user's following favorites: {{it}}")
        String recommendBasedOn(String text);
    }

    public String recommend(String input) {
        AiRecommender aiRecommender = AiServices.create(AiRecommender.class, chatLanguageModel);
        String recommendations = aiRecommender.recommendBasedOn(input);
        return recommendations;
    }
}

