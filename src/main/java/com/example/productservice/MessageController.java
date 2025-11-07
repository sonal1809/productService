package com.example.productservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @GetMapping("/message")
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000)) // will retry for 3 times
    public String getMessage(){
        logger.info("inside controller");
        RestTemplate rt = new RestTemplate();
        rt.getForObject("http://localhost:9090/demo" , Object.class);
        return "Message received successfully";
    }

    @Recover
    public String getRecoveryMessage(){
        return "Recovery message";
    }
}
