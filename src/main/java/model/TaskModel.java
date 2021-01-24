package model;

import lombok.Data;

@Data
public class TaskModel {
    private String name;
    private String link;
    private String emailSenderName;
    private String emailSenderAddress;

    public TaskModel() {
        this.emailSenderName = "Amazon Link Checker";
        this.emailSenderAddress = "no-email-listed";
    }
}
