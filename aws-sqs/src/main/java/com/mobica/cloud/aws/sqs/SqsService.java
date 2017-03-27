package com.mobica.cloud.aws.sqs;

import com.mobica.cloud.aws.db.DynamoDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import static com.mobica.cloud.aws.config.SqsConfig.SQS_NAME;

@Service
public class SqsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqsService.class);

    private final DynamoDbService dynamoDbService;

    public SqsService(DynamoDbService dynamoDbService) {
        this.dynamoDbService = dynamoDbService;
    }

    @JmsListener(destination = SQS_NAME)
    public void consumeMessage(String message) {
        LOGGER.debug("Message received: " + message);
        dynamoDbService.saveMessage(message);
    }
}
