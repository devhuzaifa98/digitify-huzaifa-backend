package com.boot.huzaifa_digitify_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionDTO {

    @JsonProperty("statement")
    private String statement;

    @JsonProperty("pollId")
    private Long pollId;

    // Default constructor (necessary for deserialization)
    public QuestionDTO() {
    }

    // Constructor with fields
    public QuestionDTO(String statement, Long pollId) {
        this.statement = statement;
        this.pollId = pollId;
    }

    // Getters and setters
    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "statement='" + statement + '\'' +
                ", pollId=" + pollId +
                '}';
    }
}
