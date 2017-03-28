package com.mobica.cloud.aws.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamoDbService {

    @Autowired
    MessageRepository messageRepository;

    public void saveMessage(String massage) {
        Message message = new Message(massage);
        messageRepository.saveMessage(message);
    }

    public Message getMessage(String message){
        return messageRepository.getMessage(message);
    }

    public void removeMessage(String message){
        messageRepository.removeMessage(message);
    }
}
