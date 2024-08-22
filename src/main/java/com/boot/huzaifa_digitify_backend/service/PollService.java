package com.boot.huzaifa_digitify_backend.service;

import com.boot.huzaifa_digitify_backend.entity.Poll;
import com.boot.huzaifa_digitify_backend.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll savePoll(Poll poll) {
        try {
            return pollRepository.save(poll);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to save poll: " + e.getMessage());
        }
    }

    // Get all polls
    public List<Poll> fetchAllPolls() {
        try {
            return pollRepository.findAll();
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch all polls: " + e.getMessage());
        }
    }

    // Get a poll by ID
    public Optional<Poll> fetchPollById(Long id) {
        try {
            return pollRepository.findById(id);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch poll by ID: " + e.getMessage());
        }
    }

    public Optional<Poll> updatePoll(Long id, Poll updatedPoll) {
        try {
            Optional<Poll> existingPollOptional = pollRepository.findById(id);
            if (existingPollOptional.isPresent()) {
                Poll existingPoll = existingPollOptional.get();
                existingPoll.setTitle(updatedPoll.getTitle());
                Poll savedEntity = pollRepository.save(existingPoll);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to update poll: " + e.getMessage());
        }
    }

    public boolean deletePoll(Long id) {
        try {
            pollRepository.deleteById(id);
            return true; // Deletion successful
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to delete poll: " + e.getMessage());
        }
    }
}
