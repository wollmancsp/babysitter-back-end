package com.springTest.SprintTest.chat;


import com.springTest.SprintTest.run.User;
import com.springTest.SprintTest.run.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@CrossOrigin("http://localhost:4200")
public class MessageController {
    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("")
    void temp() {

    }

    // Searches database to find chats with a specific userID
    @GetMapping("{userID}")
    List<Chat> findAllChatsByUserID(@PathVariable Integer userID) {
//        System.out.println("1: " + userID);
        return messageRepository.findAllChatsByUserID(userID);
    }

   // Searches database to find messages with a specific chatID
//@GetMapping("{chatID}")
//    List<Message> findAllMessagesByChatID(@PathVariable Integer chatID) {
//        System.out.println("MSG: " + chatID);
//    return messageRepository.findAllMessagesByChatID(chatID);
//}
//
//    // new message added
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    void create(@Valid @RequestBody Message message) {
//        messageRepository.create(message);
//    }
}
