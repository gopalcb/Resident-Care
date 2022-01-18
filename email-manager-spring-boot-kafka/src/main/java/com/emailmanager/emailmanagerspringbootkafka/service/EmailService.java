package com.emailmanager.emailmanagerspringbootkafka.service;

import com.emailmanager.emailmanagerspringbootkafka.config.EmailConfig;
import com.emailmanager.emailmanagerspringbootkafka.entity.Email;
import com.emailmanager.emailmanagerspringbootkafka.entity.Message;
import com.emailmanager.emailmanagerspringbootkafka.entity.Topic;
import com.emailmanager.emailmanagerspringbootkafka.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    // use user repository and resolve dependency using Autowired
    @Autowired
    private EmailRepository repository;

    @Autowired
    private MessageService messageService;

    @PersistenceContext
    private EntityManager entityManager;

    public Email saveEmail(Email email){
        return repository.save(email);
    }

    public List<Email> saveEmails(List<Email> emails){
        return repository.saveAll(emails);
    }

    public List<Email> getAllEmails(){
        return repository.findAll();
    }

    public Email getEmailById(int id){
        return repository.findById(id).orElse(null);
    }

    public boolean deleteEmail(int id){
        repository.deleteById(id);
        return true;
    }

    public List<Email> getEmailByFolder(int userId, String folder){
        List<Email> emails = repository.findEmailByFolder(userId, folder);
        return emails;
    }

    public Email send(Email email){
        // Create a mail sender
        EmailConfig config = new EmailConfig();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(config.getHost());
        mailSender.setPort(config.getPort());
        mailSender.setUsername(config.getUsername());
        mailSender.setPassword(config.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email.getEmail_from());
        mailMessage.setTo(email.getEmail_to());
        mailMessage.setSubject(email.getEmail_subject());
        mailMessage.setText(email.getEmail_body());

        // Send mail
        mailSender.send(mailMessage);

        return email;
    }

    public Message sendEMailToKafka(){
        List<Email> emails = loadFakeEmails();
        Message message = new Message();
        message.setValue(emails);
        return messageService.send(message, false, Topic.email_manager_topic.toString());
    }

    public List<Email> loadFakeEmails(){
        List<Email> emailList = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            Email email = new Email();
            email.setId(i+1);
            email.setEmail_body("Lorem ipsum dolor sit amet consectetur adipiscing elit sed do.\n" +
                    "Eiusmod tempor incididunt ut labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat duis aute irure.\n" +
                    "Dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat. Nulla pariatur excepteur sint occaecat cupidatat non proident sunt? In culpa qui officia deserunt mollit anim?\n" +
                    "Id est laborum sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium totam rem aperiam eaque.\n" +
                    "Ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae. Dicta sunt explicabo.\n");
            email.setEmail_subject("Test email subject " + (i+1));
            email.setEmail_from("test-sender@gmail.com");
            email.setEmail_to("test-receiver@gmail.com");
            email.setEmail_folder("inbox");

            emailList.add(email);
        }

        return emailList;
    }
}
