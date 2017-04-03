package com.mobica.cloud.aws.db;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import static com.mobica.cloud.aws.db.DbMessage.TABLE_NAME;

@DynamoDBTable(tableName = TABLE_NAME)
public class DbMessage {
    public static final String TABLE_NAME = "opendoors17-dev-test";
    public static final String ID_NAME = "id";
    public static final String MESSAGE_NAME = "message";

    @DynamoDBHashKey(attributeName = ID_NAME)
    private Long id;

    @DynamoDBAttribute(attributeName = MESSAGE_NAME)
    private String message;


    public DbMessage() {
    }

    public DbMessage(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DbMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
