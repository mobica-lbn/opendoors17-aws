package com.mobica.cloud.aws.sqs;

import com.mobica.cloud.aws.db.DbMessage;
import com.mobica.cloud.aws.db.DbService;
import org.springframework.stereotype.Service;

@Service
public class SqsService {

    private final DbService dbService;
    private final SqsMessageConverter sqsMessageConverter;

    public SqsService(DbService dbService, SqsMessageConverter sqsMessageConverter) {
        this.dbService = dbService;
        this.sqsMessageConverter = sqsMessageConverter;
    }

    public void processMessage(String sqsMessageJson) {
        DbMessage dbMessage = convertFrom(sqsMessageJson);
        dbService.saveMessage(dbMessage);
    }

    private DbMessage convertFrom(String sqsMessageJson) {
        SqsMessage sqsMessage = sqsMessageConverter.fromJson(sqsMessageJson);
        return new DbMessage(sqsMessage.getId(), sqsMessage.getMessage());
    }
}
