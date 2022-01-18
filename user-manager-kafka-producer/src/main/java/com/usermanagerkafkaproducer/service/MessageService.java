package com.usermanagerkafkaproducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.usermanagerkafkaproducer.entity.Message;
import com.usermanagerkafkaproducer.entity.Topic;
import com.usermanagerkafkaproducer.entity.User;
import com.usermanagerkafkaproducer.repository.UserRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageService {
    @Autowired
    private KafkaTemplate<String, Object> template;

    public String getAuthString(String email, String password){
        JSONObject obj = new JSONObject();

        obj.put("email", email);
        obj.put("password", password);

        String str = obj.toString();
        return str;
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
}

