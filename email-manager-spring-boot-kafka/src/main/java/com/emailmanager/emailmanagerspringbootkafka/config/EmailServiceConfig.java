package com.emailmanager.emailmanagerspringbootkafka.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.emailmanager.emailmanagerspringbootkafka.entity.Topic;
import com.emailmanager.emailmanagerspringbootkafka.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceConfig {
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private MessageService messageService;

    Folder emailFolder;
    Store store;
    Properties properties = new Properties();

    @PostConstruct
    void setup() {
        String server = emailConfig.getHost();
        properties.put("mail.pop3.host", server);
        properties.put("mail.pop3.port", emailConfig.getPort());
        //properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = null;
        try {
            store = emailSession.getStore("pop3s");
            store.connect(server, emailConfig.getUsername(), emailConfig.getPassword());
            emailFolder = store.getFolder("INBOX");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 5000)
    synchronized void read() throws MessagingException, IOException {
        emailFolder.open(Folder.READ_ONLY);
        Message[] messages = emailFolder.getMessages();
        messageService.send(messages, false, Topic.email_manager_topic.toString());
        emailFolder.close();
    }
}