package com.mobica.cloud.aws.sqs;

public class SqsMessage {
    private Long id;
    private String message;

    public SqsMessage(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }
}
