package com.example.demo.repository;

import com.example.demo.model.Due;
import com.example.demo.model.DueStatus;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DueRepository extends JpaRepository<Due, Long> {
    List<Due> findAllByUserAndDueStatusOrderByRepaymentDateAsc(User user, DueStatus dueStatus);

    @Query("update Due d set d.dueStatus = :dueStatus where d.id = :id")
    Boolean updateDueStatus(@Param(value = "id") long id, @Param(value = "dueStatus") DueStatus dueStatus);
}
