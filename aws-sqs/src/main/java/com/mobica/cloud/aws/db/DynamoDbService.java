package com.mobica.cloud.aws.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DynamoDbService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbService.class);

    @Autowired
    MessageRepository messageRepository;

    public void saveMessage(String massage) {
        Message message = new Message(massage);
        messageRepository.saveMessage(message);
                LOGGER.debug("Message saved: " + massage);

    }
    public Message getMessage(String message){
        return messageRepository.getMessage(message);
    }

    public void removeMessage(String message){
        messageRepository.removeMessage(message);
    }
}
