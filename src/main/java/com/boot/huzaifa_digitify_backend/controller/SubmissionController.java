package com.boot.huzaifa_digitify_backend.controller;

import com.boot.huzaifa_digitify_backend.dto.SubmissionDTO;
import com.boot.huzaifa_digitify_backend.entity.Poll;
import com.boot.huzaifa_digitify_backend.entity.Submission;
import com.boot.huzaifa_digitify_backend.service.PollService;
import com.boot.huzaifa_digitify_backend.service.SubmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final PollService pollService;

    public SubmissionController(SubmissionService submissionService, PollService pollService) {
        this.submissionService = submissionService;
        this.pollService = pollService;
    }

    @PostMapping("/submissions")
    public ResponseEntity<Submission> saveSubmission(@RequestBody SubmissionDTO submissionDTO) {
        Poll poll = pollService.fetchPollById(submissionDTO.getPollId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found"));

        Submission savedSubmission = submissionService.saveSubmission(poll);
        return ResponseEntity.ok(savedSubmission);
    }

    // Get all submissions
    @GetMapping("/submissions")
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        List<Submission> submissions = submissionService.fetchAllSubmissions();
        return ResponseEntity.ok(submissions);
    }

    // Get a submission by ID
    @GetMapping("/submissions/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        Optional<Submission> submissionOptional = submissionService.fetchSubmissionById(id);
        return submissionOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a submission
    @PutMapping("/submissions/{id}")
    public ResponseEntity<Submission> updateSubmission(@PathVariable Long id, @RequestBody Submission submission) {
        Optional<Submission> updatedSubmissionOptional = submissionService.updateSubmission(id, submission);
        return updatedSubmissionOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete a submission
    @DeleteMapping("/submissions/{id}")
    public ResponseEntity<String> deleteSubmission(@PathVariable Long id) {
        boolean deletionStatus = submissionService.deleteSubmission(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Submission with ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete submission with ID " + id);
        }
    }
}
