package com.example.todo.controller;

import com.example.todo.model.Plan;
import com.example.todo.model.dto.PlanDto;
import com.example.todo.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

//    @Autowired
    private final PlanService planService;

    @GetMapping("/{user_id}/get-plan")
    public ResponseEntity<List<Plan>> getPlansByDate(@PathVariable("user_id") Long userId,
                                                     @RequestParam String date) {
        List<Plan> plans = planService.getPlansByDate(userId, date);
        return ResponseEntity.ok(plans);
    }

    @PostMapping("/{user_id}/post-plan")
    public ResponseEntity<Plan> createPlan(@PathVariable("user_id") Long userId,
                                           @RequestBody PlanDto planDto) {
        Plan createdPlan = planService.createPlan(userId, planDto);
        return ResponseEntity.ok(createdPlan);
    }

    @PatchMapping("/{user_id}/update-plan/{plan_id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable("user_id") Long userId,
                                           @PathVariable("plan_id") Long planId,
                                           @RequestBody PlanDto planDto) {
//        Optional<Plan> plan = planService.updatePlan(userId, planId, updatedPlan);
//        return plan.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

        try {
            Optional<Plan> plan = planService.updatePlan(userId, planId, planDto);
            return plan.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }

//    @DeleteMapping("/{user_id}/{plan_id}")
//    public ResponseEntity<String> deletePlan(@PathVariable("user_id") Long userId,
//                                             @PathVariable("plan_id") Long planId) {
//        boolean result = planService.deletePlan(userId, planId);
//
//        if (result) {
//            return new ResponseEntity<>("Plan deleted successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to delete plan", HttpStatus.BAD_REQUEST);
//        }
//
//    }

    @DeleteMapping("/{userId}/delete-plan/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long userId, @PathVariable Long planId) {
        try {
            planService.deletePlan(userId, planId);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }

}

