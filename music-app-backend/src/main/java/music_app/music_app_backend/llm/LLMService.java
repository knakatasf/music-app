package music_app.music_app_backend.llm;

import music_app.music_app_backend.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.*;

 @Service
 public class LLMService {
     private final AppConfig appConfig;

     @Autowired
     public LLMService(AppConfig appConfig) {
         this.appConfig = appConfig;
     }

     interface AiRecommender {
         @UserMessage("Recommend three songs based on the user's following favorites: {{it}}")
         String[] recommendBasedOn(String text);
     }

     public String[] recommend(String input) {
         ChatLanguageModel model = OpenAiChatModel.withApiKey(appConfig.getOpenaiApiKey());
         AiRecommender aiRecommender = AiServices.create(AiRecommender.class, model);

         String[] recommends = aiRecommender.recommendBasedOn(input);
         System.out.println(recommends);
         return recommends;
     }
}

