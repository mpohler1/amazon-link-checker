package model;

import lombok.Data;

@Data
public class TaskModel {
    private String productName;
    private String productPageLink;
    private String emailSenderName;
    private String emailSenderAddress;

    public TaskModel() {
        this.emailSenderName = "Amazon Link Checker";
        this.emailSenderAddress = "no-email-listed";
    }
}
