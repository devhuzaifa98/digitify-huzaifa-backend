package com.boot.huzaifa_digitify_backend.controller;

import com.boot.huzaifa_digitify_backend.dto.QuestionDTO;
import com.boot.huzaifa_digitify_backend.entity.Poll;
import com.boot.huzaifa_digitify_backend.entity.Question;
import com.boot.huzaifa_digitify_backend.service.PollService;
import com.boot.huzaifa_digitify_backend.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;
    private final PollService pollService;

    public QuestionController(QuestionService questionService, PollService pollService) {
        this.questionService = questionService;
        this.pollService = pollService;
    }

    @PostMapping("/questions")
    public ResponseEntity<Question> saveQuestion(@RequestBody QuestionDTO questionDTO) {
//        logger.info("Received QuestionDTO: {}", questionDTO.getPollId());
        Poll poll = pollService.fetchPollById(questionDTO.getPollId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found"));

        Question savedQuestion = questionService.saveQuestion(questionDTO.getStatement(), poll);
        return ResponseEntity.ok(savedQuestion);
    }

    // Get all questions
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.fetchAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Get a question by ID
    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> questionOptional = questionService.fetchQuestionById(id);
        return questionOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a question
    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Optional<Question> updatedQuestionOptional = questionService.updateQuestion(id, question);
        return updatedQuestionOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete a question
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        boolean deletionStatus = questionService.deleteQuestion(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Question with ID " + id + " has been deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete question with ID " + id);
        }
    }
}
