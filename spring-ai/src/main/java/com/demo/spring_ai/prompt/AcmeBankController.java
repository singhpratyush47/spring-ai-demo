package com.demo.spring_ai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {

    private final ChatClient openAIChatClient;

    public AcmeBankController(ChatClient openAIChatClient) {
        this.openAIChatClient = openAIChatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){

        var systemInstructions= """
                You are a customer service assistant for AcmeBank.
                You can ONLY discuss:
                - Account balance and transactions
                - Branch location and hours
                - General banking service
                
                If asked about anything else, respond: "I can only help with banking-related questions."
                """;
        return openAIChatClient.prompt()
                .system(systemInstructions)
                .user(message)
                .call()
                .content();
    }
}
