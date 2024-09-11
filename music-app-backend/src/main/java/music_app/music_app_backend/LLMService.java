package music_app.music_app_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.*;

 @Service
 public class LLMService {
     @Value("${APIKEY_OPENAI}")
     static String APIKEY_OPENAI;

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
         ChatLanguageModel testModel = OpenAiChatModel.withApiKey(APIKEY_OPENAI);
         AiRecommender aiRecommender = AiServices.create(AiRecommender.class, testModel);
         
         String userInput = "Hikaru Utada";
         String[] recommends = aiRecommender.recommendBasedOn(userInput);
         
         for (String recommend : recommends) {
             System.out.println(recommend);
         }
    }
}

