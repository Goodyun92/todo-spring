package com.example.todo.repository;

import com.example.todo.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // userId와 날짜에 따른 일정 검색
    List<Plan> findByUser_UserIdAndDate(Long userId, String date);
}
