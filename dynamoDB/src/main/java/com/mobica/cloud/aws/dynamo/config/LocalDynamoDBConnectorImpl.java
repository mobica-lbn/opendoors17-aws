package com.mobica.cloud.aws.dynamo.config;

import org.springframework.stereotype.Component;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

@Component("local")
public class LocalDynamoDBConnectorImpl implements DynamoDBConnector {

  private DynamoDB dynamoDB;
  private AmazonDynamoDB client;


  public LocalDynamoDBConnectorImpl() {
    EndpointConfiguration location = new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2");
    
    client = AmazonDynamoDBClientBuilder.standard()
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
