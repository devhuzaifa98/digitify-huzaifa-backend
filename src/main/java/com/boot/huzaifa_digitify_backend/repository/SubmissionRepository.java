package com.boot.huzaifa_digitify_backend.repository;

import com.boot.huzaifa_digitify_backend.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {}
