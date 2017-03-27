package com.mobica.cloud.aws;

import com.mobica.cloud.aws.config.SqsConfig;
import com.mobica.cloud.aws.db.DynamoDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwsSqsApplicationTests {

    @MockBean
    private DynamoDbService dynamoDbService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void testMessageReceived() throws InterruptedException {
        //given
        CountDownLatch latch = new CountDownLatch(1);
        String message = "test_message";
        StringBuilder received = new StringBuilder();
        doAnswer(invocationOnMock -> {
            received.setLength(0);
            received.append(String.class.cast(invocationOnMock.getArguments()[0]));
            latch.countDown();
            return null;
        }).when(dynamoDbService).saveMessage(message);

        //when
        jmsTemplate.convertAndSend(SqsConfig.SQS_NAME, message);

        //then
        latch.await(15, TimeUnit.SECONDS);
        assertEquals(message, received.toString());
    }
}
