package com.mobica.cloud.aws.dynamo.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

public interface DynamoDBConnector {
  
  Table getTable(String tableName);
  AmazonDynamoDB getClient();
}
