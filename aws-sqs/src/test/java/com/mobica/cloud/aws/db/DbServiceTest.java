package com.mobica.cloud.aws.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    public void testSaveMessage() {
        //given
        Long id = -1L;
        String message = "test_message";
        DbMessage dbMessage = new DbMessage(id, message);

        //when
        dbService.saveMessage(dbMessage);

        //then
        Optional<DbMessage> messageBy = dbService.getMessageBy(id);
        assertEquals(message, messageBy.get().getMessage());
        assertEquals(id, messageBy.get().getId());

        //after
        dbService.removeMessageBy(id);
    }
}