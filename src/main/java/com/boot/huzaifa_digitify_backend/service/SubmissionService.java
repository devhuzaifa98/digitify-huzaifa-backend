package com.boot.huzaifa_digitify_backend.service;

import com.boot.huzaifa_digitify_backend.entity.Poll;
import com.boot.huzaifa_digitify_backend.entity.Submission;
import com.boot.huzaifa_digitify_backend.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public Submission saveSubmission(Poll poll) {
        try {
            Submission submission = new Submission(poll);
            return submissionRepository.save(submission);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to save submission: " + e.getMessage());
        }
    }

    // Get all submissions
    public List<Submission> fetchAllSubmissions() {
        try {
            return submissionRepository.findAll();
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch all submissions: " + e.getMessage());
        }
    }

    // Get a submission by ID
    public Optional<Submission> fetchSubmissionById(Long id) {
        try {
            return submissionRepository.findById(id);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch submission by ID: " + e.getMessage());
        }
    }

    public Optional<Submission> updateSubmission(Long id, Submission updatedSubmission) {
        try {
            Optional<Submission> existingSubmissionOptional = submissionRepository.findById(id);
            if (existingSubmissionOptional.isPresent()) {
                Submission existingSubmission = existingSubmissionOptional.get();
                existingSubmission.setPoll(updatedSubmission.getPoll());
                Submission savedEntity = submissionRepository.save(existingSubmission);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to update submission: " + e.getMessage());
        }
    }

    public boolean deleteSubmission(Long id) {
        try {
            submissionRepository.deleteById(id);
            return true; // Deletion successful
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to delete submission: " + e.getMessage());
        }
    }
}
