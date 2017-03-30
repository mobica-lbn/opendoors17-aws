package com.mobica.cloud.aws;

import com.mobica.cloud.aws.config.SqsConfig;
import com.mobica.cloud.aws.db.DynamoDbService;
import com.mobica.cloud.aws.db.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwsSqsApplicationTests {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private DynamoDbService dynamoDbService;

    @Test
    public void messageSentConsumedAndSaved() throws InterruptedException {

        //given
        String message = "integration_message";

        //when
        jmsTemplate.convertAndSend(SqsConfig.SQS_NAME, message);

        //then
        dynamoDbService.wait(10000);
        Optional<Message> messageBy = dynamoDbService.getMessageBy(message);
        assertThat(message, is(equalTo(messageBy.get())));

        //after
        dynamoDbService.removeMessageBy(message);
    }
}
