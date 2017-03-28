package com.mobica.cloud.aws.db;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamoDbServiceTest {

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @Autowired
    DynamoDbService dynamoDbService;


    @Test
    public void addMessage() throws Exception {
        String textMessage = "Hello message from Reyes";
        dynamoDbService.saveMessage(textMessage);
//        Message message = dynamoDbService.getMessage(textMessage);
//        Assert.assertThat(textMessage, CoreMatchers.containsString(message.getMessage()));
    }

    @Test
    public void removeMessage() throws Exception {
        String textMessage = "Hello message from Reyes";
        dynamoDbService.removeMessage(textMessage);
        Message message = dynamoDbService.getMessage(textMessage);
        Assert.assertNull(message);
    }
}