package com.demo.spring_ai.multimodal.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetection {

    private final ChatClient openAIChatClient;

    @Value("classpath:/images/bangalore-city-map.jpg")
    Resource sampleImage;

    public ImageDetection(ChatClient openAIChatClient){
        this.openAIChatClient=openAIChatClient;
    }

    @GetMapping("/image-to-text")
    public String imageToText(){
        return openAIChatClient.prompt()
                .user(u->{
                    u.text("Can you please describe what you see in the following image?");
                    u.media(MimeTypeUtils.IMAGE_JPEG,sampleImage);
                }).call().content();
    }

}
