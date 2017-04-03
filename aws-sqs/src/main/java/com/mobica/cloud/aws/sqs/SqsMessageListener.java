package com.mobica.cloud.aws.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsMessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqsMessageListener.class);
    private final SqsService sqsService;

    public SqsMessageListener(SqsService sqsService) {
        this.sqsService = sqsService;
    }

    @JmsListener(destination = "${aws.sqs.name}")
    public void onMessageReceived(String sqsMessageJson) {
        LOGGER.debug("Message received: " + sqsMessageJson);
        sqsService.processMessage(sqsMessageJson);
    }
}
