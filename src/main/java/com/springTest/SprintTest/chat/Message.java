package com.springTest.SprintTest.chat;
import jakarta.validation.constraints.NotEmpty;
import java.sql.Timestamp;

public record Message(
    Integer message_id,
    @NotEmpty Integer message_user_id,
    @NotEmpty String message_text,
    Timestamp message_time,
    Integer message_chat_id
) {}
