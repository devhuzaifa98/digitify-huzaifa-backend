package com.boot.huzaifa_digitify_backend.repository;

import com.boot.huzaifa_digitify_backend.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {}
