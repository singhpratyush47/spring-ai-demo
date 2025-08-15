package com.demo.spring_ai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient openAIChatClient;

    public ChatController(ChatClient openAIChatClient) {
        this.openAIChatClient = openAIChatClient;
    }

    @GetMapping("/chat")
    public String chat(){
        return openAIChatClient.prompt()
                .user("Tell me an interesting fact about java")
                .call().content();
    }

    @GetMapping("/stream")
    public Flux<String> stream(){
        return openAIChatClient.prompt()
                .user("i'm visiting varanasi soon, can you give me 10 places i must visit?")
                .stream().content();
    }

    @GetMapping("/joke")
    public ChatResponse joke(){
        return openAIChatClient.prompt()
                .user("Tell me a dad joke about dogs")
                .call()
                .chatResponse();
    }
}
