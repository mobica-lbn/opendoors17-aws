package com.mobica.cloud.aws.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamoDbServiceTest {

    @Autowired
    private DynamoDbService dynamoDbService;

    @Test
    public void testSaveMessage() {
        //given
        String message = "db_test_msg";

        //when
        dynamoDbService.saveMessage(message);

        //then
        Optional<Message> messageBy = dynamoDbService.getMessageBy(message);
        assertEquals(message, messageBy.get().getMessage());

        //after
        dynamoDbService.removeMessageBy(message);
    }
}