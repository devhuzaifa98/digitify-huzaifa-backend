package com.boot.huzaifa_digitify_backend.service;

import com.boot.huzaifa_digitify_backend.entity.Poll;
import com.boot.huzaifa_digitify_backend.entity.Question;
import com.boot.huzaifa_digitify_backend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(String statement, Poll poll) {
        try {
            Question question = new Question(statement, poll);
            return questionRepository.save(question);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to save question: " + e.getMessage());
        }
    }

    // Get all questions
    public List<Question> fetchAllQuestions() {
        try {
            return questionRepository.findAll();
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch all questions: " + e.getMessage());
        }
    }

    // Get a question by ID
    public Optional<Question> fetchQuestionById(Long id) {
        try {
            return questionRepository.findById(id);
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to fetch question by ID: " + e.getMessage());
        }
    }

    public Optional<Question> updateQuestion(Long id, Question updatedQuestion) {
        try {
            Optional<Question> existingQuestionOptional = questionRepository.findById(id);
            if (existingQuestionOptional.isPresent()) {
                Question existingQuestion = existingQuestionOptional.get();
                existingQuestion.setStatement(updatedQuestion.getStatement());
                Question savedEntity = questionRepository.save(existingQuestion);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to update question: " + e.getMessage());
        }
    }

    public boolean deleteQuestion(Long id) {
        try {
            questionRepository.deleteById(id);
            return true; // Deletion successful
        } catch (Exception e) {
            // Handle exception or log the error
            throw new RuntimeException("Failed to delete question: " + e.getMessage());
        }
    }
}
