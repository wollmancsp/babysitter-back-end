package com.springTest.SprintTest.chat;

import com.springTest.SprintTest.run.User;
import com.springTest.SprintTest.run.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcClient jdbcClient;
    public MessageRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Chat> findAllChatsByUserID(Integer userID) {
//        var ucSearch = jdbcClient.sql("SELECT ucChatID FROM UserChat WHERE ucUserID = user_ID").param("ucUserID", userID).query(UserChat.class).optional();
//        var chatSearch jdbcClient.sql("SELECT * FROM Chat WHERE chat_id = ucChatID").param("chat_id", ucSearch).query(Chat.class).optional();
//        return chatSearch;

        //WORKS: return jdbcClient.sql("SELECT * FROM Chat WHERE chat_id = (SELECT ucChatID FROM UserChat WHERE ucUserID = :userID)").param("userID", userID).query(Chat.class).list();
        //Returns just chat ID

//        System.out.println("2: " + userID);
        List<Chat> chatList = new ArrayList<>();
        List<Integer> chatIDs = jdbcClient.sql("SELECT chat_ID FROM Chat WHERE chat_id = (SELECT ucChatID FROM UserChat WHERE ucUserID = :userID)").param("userID", userID).query(Integer.class).stream().toList();
        for(var chatID : chatIDs) {
            String[] userIDList = jdbcClient.sql("SELECT ucUserID FROM UserChat WHERE ucChatID = :chatID").param("chatID", chatID).query(String.class).stream().toArray(String[]::new);
            Message[] messageList = jdbcClient.sql("SELECT * FROM Message WHERE message_chat_id = :chatID").param("chatID", chatID).query(Message.class).stream().toArray(Message[]::new);
            chatList.add(new Chat(chatID, userIDList, messageList));
        }
        return chatList;
    }

//    public List<Message> findAllMessagesByChatID(Integer chatID) {
//        return jdbcClient.sql("SELECT message_id FROM Message WHERE message_chat_ID = :chatID").param("chatID", chatID).query(Message.class).list();
//    }
}
