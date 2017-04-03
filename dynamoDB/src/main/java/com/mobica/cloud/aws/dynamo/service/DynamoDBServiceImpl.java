package com.mobica.cloud.aws.dynamo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.mobica.cloud.aws.dynamo.config.DynamoDBConnector;

import java.util.List;
import java.util.Map;

@Service
public class DynamoDBServiceImpl implements DynamoDBService {

  @Autowired
  @Qualifier("remote")
  private DynamoDBConnector dynamoDb;

  @Override
  public Item findById(int id) throws ResourceNotFoundException {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey("id", id);
    return dynamoDb.getTable(TABLE_NAME).getItem(spec);
  }

  @Override
  public List<Map<String, AttributeValue>> fetchAll() throws ResourceNotFoundException {
    ScanRequest scanRequest = new ScanRequest().withTableName(TABLE_NAME);
    ScanResult result = dynamoDb.getClient().scan(scanRequest);
    return result.getItems();
  }
}
