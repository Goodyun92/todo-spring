package com.example.todo.service;

import com.example.todo.model.Plan;
import com.example.todo.model.dto.PlanDto;
import com.example.todo.repository.PlanRepository;
import com.example.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanService {

    @Autowired
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public Plan createPlan(Long userId, PlanDto planDto) {
        // 일정 생성 로직
        Plan newPlan = new Plan();
        newPlan.setContent(planDto.getContent());
        newPlan.setUser(userRepository.findByUserId(userId).get());
        newPlan.setDate(planDto.getDate());
        newPlan.setCheckStatus(false);
        newPlan.setReview("");
        return planRepository.save(newPlan);
    }

    public Optional<Plan> updatePlan(Long userId, Long planId, PlanDto planDto) {
        // 일정 수정 로직
        Optional<Plan> optionalPlan = planRepository.findById(planId);

        if (!optionalPlan.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Plan with given ID not found"
            );
        }

        Plan existingPlan = optionalPlan.get();

        if (!existingPlan.getUser().getUserId().equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "User does not have permission to update this plan"
            );
        }

        if (planDto.getContent() != null) {
            existingPlan.setContent(planDto.getContent());
        }

        if (planDto.getDate() != null) {
            existingPlan.setDate(planDto.getDate());
        }

        if (planDto.getReview() != null) {
            existingPlan.setReview(planDto.getReview());
        }

        if (planDto.getCheckStatus() != null) {
            existingPlan.setCheckStatus(planDto.getCheckStatus());
        }

        return Optional.of(planRepository.save(existingPlan));

    }

    public void deletePlan(long delUserId, Long planId) {
        // 일정 삭제 로직

        //해당 user 자신의 plan이 맞는경우 삭제
        Optional<Plan> optionalPlan = planRepository.findById(planId);

        if (!optionalPlan.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Plan with given ID not found"
            );
        }

        Plan existingPlan = optionalPlan.get();

        if (!existingPlan.getUser().getUserId().equals(delUserId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "User does not have permission to delete this plan"
            );
        }

        planRepository.delete(existingPlan);
    }

    public List<Plan> getPlansByDate(Long userId, String date) {
        // 특정 날짜의 모든 일정을 가져오는 로직
        return planRepository.findByUser_UserIdAndDate(userId, date);
    }
}

