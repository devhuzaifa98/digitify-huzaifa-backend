package com.boot.huzaifa_digitify_backend.service;

import com.boot.huzaifa_digitify_backend.entity.Poll;
import com.boot.huzaifa_digitify_backend.entity.Question;
import com.boot.huzaifa_digitify_backend.entity.Response;
import com.boot.huzaifa_digitify_backend.entity.Submission;
import com.boot.huzaifa_digitify_backend.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {


    private final ResponseRepository responseRepository;

    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public Response saveResponse(String value, Submission submission, Question question) {
        try {
            Response response = new Response(value, submission, question);
            return responseRepository.save(response);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to save response: " + e.getMessage());
        }
    }

    // Get all responses
    public List<Response> fetchAllResponses() {
        try {
            return responseRepository.findAll();
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch all responses: " + e.getMessage());
        }
    }

    // Get a response by ID
    public Optional<Response> fetchResponseById(Long id) {
        try {
            return responseRepository.findById(id);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch response by ID: " + e.getMessage());
        }
    }

    public Optional<Response> updateResponse(Long id, Response updatedResponse) {
        try {
            Optional<Response> existingResponseOptional = responseRepository.findById(id);
            if (existingResponseOptional.isPresent()) {
                Response existingResponse = existingResponseOptional.get();
                existingResponse.setQuestion(updatedResponse.getQuestion());
                existingResponse.setSubmission(updatedResponse.getSubmission());
                existingResponse.setValue(updatedResponse.getValue());
                Response savedEntity = responseRepository.save(existingResponse);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to update response: " + e.getMessage());
        }
    }

    public boolean deleteResponse(Long id) {
        try {
            responseRepository.deleteById(id);
            return true; // Deletion successful
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to delete response: " + e.getMessage());
        }
    }
}
