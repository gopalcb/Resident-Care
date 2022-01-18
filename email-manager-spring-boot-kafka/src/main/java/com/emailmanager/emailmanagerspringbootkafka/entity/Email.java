package com.emailmanager.emailmanagerspringbootkafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_emails")
public class Email {
    @Id
    @GeneratedValue
    private int id;

    private String email_subject;
    private String email_body;

    private String email_from; // comma separated string
    private String email_to; // comma separated string
    private String email_cc; // comma separated string
    private String email_bcc; // comma separated string

    private String email_folder; // inbox, sent, draft, spam, trash

    private int to_user_id;

    private boolean is_read;
    private Date created_on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail_subject() {
        return email_subject;
    }

    public void setEmail_subject(String email_subject) {
        this.email_subject = email_subject;
    }

    public String getEmail_body() {
        return email_body;
    }

    public void setEmail_body(String email_body) {
        this.email_body = email_body;
    }

    public String getEmail_from() {
        return email_from;
    }

    public void setEmail_from(String email_from) {
        this.email_from = email_from;
    }

    public String getEmail_to() {
        return email_to;
    }

    public void setEmail_to(String email_to) {
        this.email_to = email_to;
    }

    public String getEmail_cc() {
        return email_cc;
    }

    public void setEmail_cc(String email_cc) {
        this.email_cc = email_cc;
    }

    public String getEmail_bcc() {
        return email_bcc;
    }

    public void setEmail_bcc(String email_bcc) {
        this.email_bcc = email_bcc;
    }

    public String getEmail_folder() {
        return email_folder;
    }

    public void setEmail_folder(String email_folder) {
        this.email_folder = email_folder;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
}
