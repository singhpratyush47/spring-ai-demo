package com.demo.spring_ai.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {

    private final ChatClient openAIChatClient;

    public VacationPlan(ChatClient openAIChatClient) {
        this.openAIChatClient = openAIChatClient;
    }

    @GetMapping("/vacation/unstructured")
    public String unstructured(){
        return openAIChatClient.prompt()
                .user("I want to plan a trip to Hawaii. Give me a list of things to do")
                .call()
                .content();
    }

    @GetMapping("/vacation/structured")
    public Itinerary structured(){
        return openAIChatClient.prompt()
                .user("I want to plan a trip to Hawaii. Give me a list of things to do")
                .call()
                .entity(Itinerary.class);
    }

}
