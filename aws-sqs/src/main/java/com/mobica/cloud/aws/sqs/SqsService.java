package com.mobica.cloud.aws.sqs;

import com.mobica.cloud.aws.db.DynamoDbService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsService {
    private static final String SQS_NAME = "opendoors17-dev-test";

    private final DynamoDbService dynamoDbService;

    public SqsService(DynamoDbService dynamoDbService) {
        this.dynamoDbService = dynamoDbService;
    }

    @JmsListener(destination = SQS_NAME)
    public void consumeMessage(String message) {
        dynamoDbService.saveMessage(message);
    }
}
