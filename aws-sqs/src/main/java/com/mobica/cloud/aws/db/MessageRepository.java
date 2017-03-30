package com.mobica.cloud.aws.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MessageRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRepository.class);

    private final DynamoDBMapper mapper;

    public MessageRepository(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public void saveMessage(Message message) {
        mapper.save(message);

        LOGGER.debug("Message saved: " + message);
    }

    public void removeMessage(String message) {
        Optional<Message> readMessage = getMessage(message);
        readMessage.ifPresent(msg -> {
            mapper.delete(msg);
            LOGGER.debug("Message deleted: " + msg);
        });
    }

    public Optional<Message> getMessage(String message) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":message", new AttributeValue().withS(message));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("message = :message")
                .withExpressionAttributeValues(eav);

        List<Message> scanResult = mapper.scan(Message.class, scanExpression);
        return scanResult.isEmpty() ? Optional.empty() : Optional.of(scanResult.get(0));
    }

}
