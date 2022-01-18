package com.emailmanager.emailmanagerspringbootkafka.controller;

import com.emailmanager.emailmanagerspringbootkafka.entity.Email;
import com.emailmanager.emailmanagerspringbootkafka.entity.Message;
import com.emailmanager.emailmanagerspringbootkafka.service.EmailService;
import com.emailmanager.emailmanagerspringbootkafka.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageService messageService;

    /** PostMapping **/
    @PostMapping("/add-user")
    public Email addUser(@RequestBody Email email){
        return null;
    }

    /** GetMapping **/
    @GetMapping("/send-email")
    public Message sendEMailToKafka(){
        return emailService.sendEMailToKafka();
    }

    @GetMapping("/get-emails")
    public List<Email> getEmails(){
        List<Email> emails = emailService.loadFakeEmails();
        return emails;
    }
}
