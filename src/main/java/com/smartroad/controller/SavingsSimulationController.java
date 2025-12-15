package com.smartroad.controller;

import com.smartroad.demo.DemoScenarioNotFoundException;
import com.smartroad.dto.SavingsSimulationRequest;
import com.smartroad.dto.SavingsSimulationResponse;
import com.smartroad.service.SavingsSimulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Tag(name = "Savings Simulation", description = "Estimate yearly savings and ROI across demo scenarios.")
public class SavingsSimulationController {

    private static final Logger log = LoggerFactory.getLogger(SavingsSimulationController.class);

    private final SavingsSimulationService savingsSimulationService;

    public SavingsSimulationController(SavingsSimulationService savingsSimulationService) {
        this.savingsSimulationService = savingsSimulationService;
    }

    @PostMapping("/simulate-savings")
    @Operation(
            summary = "Simulate savings",
            description = "Calculates potential savings, additional time, and ROI for multiple predefined scenarios."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Simulation completed",
                    content = @Content(schema = @Schema(implementation = SavingsSimulationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid simulation input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Scenario not found", content = @Content)
    })
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
