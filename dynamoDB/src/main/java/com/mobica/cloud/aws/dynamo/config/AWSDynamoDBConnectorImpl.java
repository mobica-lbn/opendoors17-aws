package com.mobica.cloud.aws.dynamo.config;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

@Component("remote")
public class AWSDynamoDBConnectorImpl implements DynamoDBConnector {

  private DynamoDB dynamoDB;
  private AmazonDynamoDB client;

  public AWSDynamoDBConnectorImpl() {
    
    AWSStaticCredentialsProvider awsCredentials = new AWSStaticCredentialsProvider(
        new BasicAWSCredentials("AKIAIPDCIDDUP6DEUGAA", "gJTD2cGV45sKXJy5f/k8xGnKRLFshOi9CBEHRsTu"));
    EndpointConfiguration location = new AwsClientBuilder.EndpointConfiguration("dynamodb.us-east-1.amazonaws.com", "us-east-1");
    
    client = AmazonDynamoDBClientBuilder.standard()
        .withCredentials(awsCredentials)
        .withEndpointConfiguration(location)
        .build();
    dynamoDB = new DynamoDB(client);
  }

  @Override
  public Table getTable(String tableName) {
    return dynamoDB.getTable(tableName);
  }

  @Override
  public AmazonDynamoDB getClient() {
    return client;
  }
}
