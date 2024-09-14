package music_app.music_app_backend;

import music_app.music_app_backend.keys.ApiKeys;
import org.springframework.stereotype.Service;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.*;

 @Service
 public class LLMService {

     ChatLanguageModel model = OpenAiChatModel.withApiKey("demo");
     
     interface AiRecommender { 
         @UserMessage("Recommend three songs based on the user's following favorites: {{it}}") 
         String[] recommendBasedOn(String text);
     }
     
     String[] recommend(String input) {
         AiRecommender aiRecommender = AiServices.create(AiRecommender.class, model);
         String[] recommends = aiRecommender.recommendBasedOn(input);
         return recommends;
     }
     
     public static void main(String[] args) {
         ChatLanguageModel testModel = OpenAiChatModel.withApiKey(ApiKeys.API_KEY_OPEN_AI);
         AiRecommender aiRecommender = AiServices.create(AiRecommender.class, testModel);
         
         String userInput = "Hikaru Utada";
         String[] recommends = aiRecommender.recommendBasedOn(userInput);
         
         for (String recommend : recommends) {
             System.out.println(recommend);
         }
    }
}

