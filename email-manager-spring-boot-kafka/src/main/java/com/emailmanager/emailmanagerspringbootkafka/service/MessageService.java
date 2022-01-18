package com.emailmanager.emailmanagerspringbootkafka.service;

import com.emailmanager.emailmanagerspringbootkafka.entity.Email;
import com.emailmanager.emailmanagerspringbootkafka.entity.Message;
import com.emailmanager.emailmanagerspringbootkafka.entity.User;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class MessageService {
    @Autowired
    private KafkaTemplate<String, Object> template;

    public KafkaProducer initKafkaProducer(){
        Properties properties = new Properties();
        properties.setProperty("boostrap.server", "127.0.0.1:9092");
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "10");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");

        KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<String, Object>(properties);
        return kafkaProducer;
    }

    public String getAuthString(String email, String password){
        JSONObject obj = new JSONObject();

        obj.put("email", email);
        obj.put("password", password);

        String str = obj.toString();
        return str;
    }

    public Message sendRecord(Object value, boolean isAuth, String topic){
        Message message = new Message();

        message.setHeader("");
        message.setValue(value);

        ProducerRecord<String, Object> record = new ProducerRecord<String, Object>(topic, message.getValue());

        if (isAuth){
            record.headers().add("auth", String.valueOf(true).getBytes());
            //record.headers().add("auth-string", getAuthString())
        } else {
            record.headers().add("auth", String.valueOf(false).getBytes());
        }

        KafkaProducer<String, Object> kafkaProducer = initKafkaProducer();
        kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (record == null){
                    System.out.println("Success");
                    System.out.println(metadata.toString());
                }  else{
                    exception.printStackTrace();
                }
            }
        });
        kafkaProducer.flush();
        kafkaProducer.close();

        return message;
    }

    public Message send(Object value, boolean isAuth, String topic){
        Message message = new Message();

        message.setHeader("");
        message.setValue(value);

        ProducerRecord<String, Object> record = new ProducerRecord<String, Object>(topic, message.getValue());

        if (isAuth){
            record.headers().add("auth", String.valueOf(true).getBytes());
            //record.headers().add("auth-string", getAuthString())
        } else {
            record.headers().add("auth", String.valueOf(false).getBytes());
        }

        ListenableFuture<SendResult<String, Object>> future = template.send(record);

        // add callback
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                //System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                //System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });

        return message;
    }

    public Email fillEmailObjectFromUser(User user){
        Email email = new Email();

        email.setEmail_subject("User created successfully");
        email.setEmail_body("Hello" + user.getFirst_name() + ",\n Your user account has been created successfully. Please keep this email for your future reference.\n\n Thank you.\n\nResident Care Team");
        email.setEmail_to(user.getEmail());
        email.setEmail_from("gopalcbala@gmail.com");
        email.setEmail_folder("sent");
        email.setTo_user_id(user.getId());

        return email;
    }

    @KafkaListener(topics = "email_manager_topic", groupId = "xx", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMessage(String message){
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(message);
            System.out.println(json.get("id"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(message);
    }
}
