package com.smartroad.controller;

import com.smartroad.demo.DemoScenarioNotFoundException;
import com.smartroad.demo.DemoScenarioService;
import com.smartroad.dto.OptimizationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Tag(name = "Demo Optimization", description = "Run predefined optimization scenarios for demo purposes.")
public class DemoOptimizationController {

    private static final Logger log = LoggerFactory.getLogger(DemoOptimizationController.class);

    private final DemoScenarioService demoScenarioService;

    public DemoOptimizationController(DemoScenarioService demoScenarioService) {
        this.demoScenarioService = demoScenarioService;
    }

    @GetMapping("/optimize/{scenarioId}")
    @Operation(
            summary = "Optimize a demo scenario",
            description = "Returns an optimization response for a predefined scenario, useful for demo and testing."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Demo optimization ready",
                    content = @Content(schema = @Schema(implementation = OptimizationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Scenario not found", content = @Content)
    })
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
