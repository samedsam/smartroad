package com.smartroad.controller;

import com.smartroad.demo.DemoScenarioNotFoundException;
import com.smartroad.demo.DemoScenarioService;
import com.smartroad.dto.OptimizationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoOptimizationController {

    private static final Logger log = LoggerFactory.getLogger(DemoOptimizationController.class);

    private final DemoScenarioService demoScenarioService;

    public DemoOptimizationController(DemoScenarioService demoScenarioService) {
        this.demoScenarioService = demoScenarioService;
    }

    @GetMapping("/optimize/{scenarioId}")
    public ResponseEntity<OptimizationResponse> optimizeDemo(@PathVariable String scenarioId) {
        try {
            OptimizationResponse response = demoScenarioService.optimizeScenario(scenarioId);
            log.info("Demo optimization ready for scenario {}", scenarioId);
            return ResponseEntity.ok(response);
        } catch (DemoScenarioNotFoundException ex) {
            log.warn("Requested unknown demo scenario: {}", scenarioId);
            return ResponseEntity.notFound().build();
        }
    }
}
