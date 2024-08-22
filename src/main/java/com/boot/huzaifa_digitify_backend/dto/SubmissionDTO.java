package com.boot.huzaifa_digitify_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmissionDTO {

    @JsonProperty("pollId")
    private Long pollId; // Define the pollId field

    // Default constructor
    public SubmissionDTO() {
    }

    // Constructor with pollId
    public SubmissionDTO(Long pollId) {
        this.pollId = pollId;
    }

    // Getter for pollId
    public Long getPollId() {
        return pollId;
    }

    // Setter for pollId
    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    // Optional: Override toString() for better logging/debugging
    @Override
    public String toString() {
        return "SubmissionDTO{" +
                "pollId=" + pollId +
                '}';
    }
}
