package com.mobica.cloud.aws.db;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DynamoDbService {

    private final MessageRepository messageRepository;

    public DynamoDbService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String massage) {
        Message message = new Message(massage);
        messageRepository.saveMessage(message);
    }

    public Optional<Message> getMessageBy(String message) {
        return messageRepository.getMessage(message);
    }

    public void removeMessageBy(String message) {
        messageRepository.removeMessage(message);
    }
}
