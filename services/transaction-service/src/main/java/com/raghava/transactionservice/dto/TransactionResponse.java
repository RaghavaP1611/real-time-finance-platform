package com.raghava.transactionservice.dto;

public class TransactionResponse {
    private String transactionId;
    private String status;
    private String topic;

    public TransactionResponse(String transactionId, String status, String topic) {
        this.transactionId = transactionId;
        this.status = status;
        this.topic = topic;
    }

    public String getTransactionId() {
        return transactionId;
    }
    public String getStatus() {
        return status;
     }
    
     public String getTopic() {
        return topic;
     }
}