package com.boot.huzaifa_digitify_backend.controller;

import com.boot.huzaifa_digitify_backend.entity.Poll;
import com.boot.huzaifa_digitify_backend.service.PollService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/polls")
    public ResponseEntity<Poll> savePoll(@RequestBody Poll poll) {
        Poll savedPoll = pollService.savePoll(poll);
        return ResponseEntity.ok(savedPoll);
    }

    // Get all polls
    @GetMapping("/polls")
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls = pollService.fetchAllPolls();
        return ResponseEntity.ok(polls);
    }

    // Get a poll by ID
    @GetMapping("/polls/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
        Optional<Poll> pollOptional = pollService.fetchPollById(id);
        return pollOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a poll
    @PutMapping("/polls/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long id, @RequestBody Poll poll) {
        Optional<Poll> updatedPollOptional = pollService.updatePoll(id, poll);
        return updatedPollOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete a poll
    @DeleteMapping("/polls/{id}")
    public ResponseEntity<String> deletePoll(@PathVariable Long id) {
        boolean deletionStatus = pollService.deletePoll(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Poll with ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete poll with ID " + id);
        }
    }
}
