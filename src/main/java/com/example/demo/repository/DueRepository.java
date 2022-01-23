package com.example.demo.repository;

import com.example.demo.model.Due;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DueRepository extends JpaRepository<Due, Long> {
}
