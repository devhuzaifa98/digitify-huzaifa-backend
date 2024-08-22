package com.boot.huzaifa_digitify_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {

    @JsonProperty("value")
    private String value; // Field for the response value

    @JsonProperty("questionId")
    private Long questionId; // Field for the ID of the question

    @JsonProperty("submissionId")
    private Long submissionId; // Field for the ID of the submission

    // Default constructor
    public ResponseDTO() {
    }

    // Constructor with parameters
    public ResponseDTO(String value, Long questionId, Long submissionId) {
        this.value = value;
        this.questionId = questionId;
        this.submissionId = submissionId;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Setter for value
    public void setValue(String value) {
        this.value = value;
    }

    // Getter for questionId
    public Long getQuestionId() {
        return questionId;
    }

    // Setter for questionId
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    // Getter for submissionId
    public Long getSubmissionId() {
        return submissionId;
    }

    // Setter for submissionId
    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    // Optional: Override toString() for better logging/debugging
    @Override
    public String toString() {
        return "ResponseDTO{" +
                "value='" + value + '\'' +
                ", questionId=" + questionId +
                ", submissionId=" + submissionId +
                '}';
    }
}
