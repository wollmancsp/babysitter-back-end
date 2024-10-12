package com.springTest.SprintTest.chat;

public record Chat(
        Integer chat_ID,
        String[] users_id_array,
        Message[] messages_array
) {}
