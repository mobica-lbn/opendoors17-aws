package com.mobica.cloud.aws.db;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MessageRepository {

    @Autowired
    DynamoDBMapper mapper;

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    public void saveMessage(Message message){
        mapper.save(message);
        viewAllMessages();
    }

    public void removeMessage(String message){
        Message messageObj = getMessage(message);
        if(messageObj != null) {
            mapper.delete(messageObj);
        }
    }

    public Message getMessage(String message){
        HashMap<String, AttributeValue> parameters = new HashMap<String, AttributeValue>();
        parameters.put(":message", new AttributeValue().withS(message));

        DynamoDBQueryExpression<Message> queryExpression = new DynamoDBQueryExpression<Message>()
                .withKeyConditionExpression("message = :message")
                .withExpressionAttributeValues(parameters);

        return mapper.query(Message.class, queryExpression).get(0);
    }

    public void viewAllMessages(){
        ScanRequest scanRequest = new ScanRequest().withTableName("Messages");

        ScanResult result = amazonDynamoDB.scan(scanRequest);
        for (Map<String, AttributeValue> item : result.getItems()){
            System.out.println(item);
        }
    }
}
