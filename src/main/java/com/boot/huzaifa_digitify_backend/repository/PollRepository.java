package com.boot.huzaifa_digitify_backend.repository;

import com.boot.huzaifa_digitify_backend.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {}