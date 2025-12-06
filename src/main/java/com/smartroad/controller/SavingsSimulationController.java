package com.smartroad.controller;

import com.smartroad.demo.DemoScenarioNotFoundException;
import com.smartroad.dto.SavingsSimulationRequest;
import com.smartroad.dto.SavingsSimulationResponse;
import com.smartroad.service.SavingsSimulationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class SavingsSimulationController {

    private static final Logger log = LoggerFactory.getLogger(SavingsSimulationController.class);

    private final SavingsSimulationService savingsSimulationService;

    public SavingsSimulationController(SavingsSimulationService savingsSimulationService) {
        this.savingsSimulationService = savingsSimulationService;
    }

    @PostMapping("/simulate-savings")
    public ResponseEntity<SavingsSimulationResponse> simulateSavings(@RequestBody SavingsSimulationRequest request) {
        try {
            SavingsSimulationResponse response = savingsSimulationService.simulateSavings(request);
            log.info("Savings simulation ready for {} scenarios", response.getScenarioResults().size());
            return ResponseEntity.ok(response);
        } catch (DemoScenarioNotFoundException ex) {
            log.warn("Requested unknown demo scenario in savings simulation");
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException ex) {
            log.warn("Invalid savings simulation input: {}", ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
