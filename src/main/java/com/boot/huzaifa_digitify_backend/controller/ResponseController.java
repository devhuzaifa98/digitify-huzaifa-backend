package com.boot.huzaifa_digitify_backend.controller;

import com.boot.huzaifa_digitify_backend.dto.ResponseDTO;
import com.boot.huzaifa_digitify_backend.entity.Question;
import com.boot.huzaifa_digitify_backend.entity.Response;
import com.boot.huzaifa_digitify_backend.entity.Submission;
import com.boot.huzaifa_digitify_backend.service.QuestionService;
import com.boot.huzaifa_digitify_backend.service.ResponseService;
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
public class ResponseController {
    private static final Logger logger = LoggerFactory.getLogger(ResponseController.class);

    private final ResponseService responseService;
    private final SubmissionService submissionService;
    private final QuestionService questionService;

    public ResponseController(ResponseService responseService, SubmissionService submissionService, QuestionService questionService) {
        this.responseService = responseService;
        this.submissionService = submissionService;
        this.questionService = questionService;
    }

    @PostMapping("/responses")
    public ResponseEntity<Response> saveResponse(@RequestBody ResponseDTO responseDTO) {
//        logger.info("Received ResponseDTO: {}", responseDTO.getPollId());
        Submission submission = submissionService.fetchSubmissionById(responseDTO.getSubmissionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Submission not found"));

        Question question = questionService.fetchQuestionById(responseDTO.getQuestionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));

        Response savedResponse = responseService.saveResponse(responseDTO.getValue(), submission, question);
        return ResponseEntity.ok(savedResponse);
    }

    // Get all responses
    @GetMapping("/responses")
    public ResponseEntity<List<Response>> getAllResponses() {
        List<Response> responses = responseService.fetchAllResponses();
        return ResponseEntity.ok(responses);
    }

    // Get a response by ID
    @GetMapping("/responses/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable Long id) {
        Optional<Response> responseOptional = responseService.fetchResponseById(id);
        return responseOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a response
    @PutMapping("/responses/{id}")
    public ResponseEntity<Response> updateResponse(@PathVariable Long id, @RequestBody Response response) {
        Optional<Response> updatedResponseOptional = responseService.updateResponse(id, response);
        return updatedResponseOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete a response
    @DeleteMapping("/responses/{id}")
    public ResponseEntity<String> deleteResponse(@PathVariable Long id) {
        boolean deletionStatus = responseService.deleteResponse(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Response with ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete response with ID " + id);
        }
    }
}
