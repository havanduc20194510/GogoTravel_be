package com.haduc.go_travel_system.config;

import org.springframework.ai.image.ImageClient;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {
    @Bean
    ImageClient imageClient(@Value("${spring.ai.openai.api-key}") String openaiApiKey) {
        return new OpenAiImageClient(new OpenAiImageApi(openaiApiKey));
    }
}
