package com.smartroad.service;

import com.smartroad.demo.DemoScenarioNotFoundException;
import com.smartroad.demo.DemoScenarioService;
import com.smartroad.dto.OptimizationResult;
import com.smartroad.dto.ScenarioSimulationResult;
import com.smartroad.dto.SavingsSimulationRequest;
import com.smartroad.dto.SavingsSimulationResponse;
import com.smartroad.dto.SimulationScenarioInput;
import com.smartroad.dto.ScoreBreakdown;
import com.smartroad.dto.ScoredRoute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavingsSimulationService {

    private final DemoScenarioService demoScenarioService;
    private final RoiAnalysisService roiAnalysisService;

    public SavingsSimulationService(DemoScenarioService demoScenarioService, RoiAnalysisService roiAnalysisService) {
        this.demoScenarioService = demoScenarioService;
        this.roiAnalysisService = roiAnalysisService;
    }

    public SavingsSimulationResponse simulateSavings(SavingsSimulationRequest request) throws DemoScenarioNotFoundException {
        List<ScenarioSimulationResult> scenarioResults = new ArrayList<>();
        double totalSavings = 0;
        double totalExtraMinutes = 0;

        if (request.getScenarios() != null) {
            for (SimulationScenarioInput scenarioInput : request.getScenarios()) {
                if (scenarioInput.getTripsPerYear() < 0) {
                    throw new IllegalArgumentException("tripsPerYear must be non-negative");
                }
                ScenarioSimulationResult result = simulateScenario(scenarioInput, request);
                scenarioResults.add(result);
                totalSavings += result.getYearlySavings();
                totalExtraMinutes += result.getYearlyExtraMinutes();
            }
        }

        Double subscriptionPrice = request.getSubscriptionPricePerYear();
        Double netGain = subscriptionPrice != null ? totalSavings - subscriptionPrice : null;
        Double roiPercentage = (subscriptionPrice != null && subscriptionPrice != 0)
                ? (netGain / subscriptionPrice) * 100
                : null;
        String roiCategory = roiAnalysisService.classifyRoiCategory(roiPercentage);
        String roiExplanation = roiAnalysisService.buildRoiExplanation(totalSavings, subscriptionPrice, roiPercentage);

        return new SavingsSimulationResponse(
                scenarioResults,
                totalSavings,
                totalExtraMinutes,
                subscriptionPrice,
                netGain,
                roiPercentage,
                roiCategory,
                roiExplanation
        );
    }

    private ScenarioSimulationResult simulateScenario(SimulationScenarioInput scenarioInput, SavingsSimulationRequest request) {
        OptimizationResult optimizationResult = demoScenarioService
                .optimizeScenarioResult(scenarioInput.getScenarioId(), request.getUserProfile());
        ScoredRoute bestRoute = optimizationResult.getBestRoute();
        ScoreBreakdown breakdown = bestRoute.getBreakdown();

        double yearlySavings = breakdown.getDeltaCost() * scenarioInput.getTripsPerYear();
        double yearlyExtraMinutes = breakdown.getDeltaMinutes() * scenarioInput.getTripsPerYear();

        return new ScenarioSimulationResult(
                scenarioInput.getScenarioId(),
                scenarioInput.getTripsPerYear(),
                breakdown.getDeltaCost(),
                breakdown.getDeltaMinutes(),
                yearlySavings,
                yearlyExtraMinutes,
                bestRoute.getDealType(),
                bestRoute.getExplanation()
        );
    }
}
