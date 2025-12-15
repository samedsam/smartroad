package com.smartroad.service;

import org.springframework.stereotype.Service;

@Service
public class RoiAnalysisService {

    public String classifyRoiCategory(Double roiPercentage) {
        if (roiPercentage == null) {
            return "UNKNOWN";
        }
        if (roiPercentage >= 100) {
            return "HIGH";
        }
        if (roiPercentage >= 30) {
            return "MEDIUM";
        }
        if (roiPercentage > 0) {
            return "LOW";
        }
        return "NEGATIVE";
    }

    public String buildRoiExplanation(double totalYearlySavings, Double subscriptionPricePerYear, Double roiPercentage) {
        if (subscriptionPricePerYear == null) {
            return String.format("Estimated annual savings: €%.2f. Provide a subscription price to evaluate ROI.",
                    totalYearlySavings);
        }

        if (subscriptionPricePerYear == 0) {
            return String.format("Estimated annual savings: €%.2f with no subscription cost.", totalYearlySavings);
        }

        if (roiPercentage == null) {
            return "ROI unavailable because subscription price is zero or missing.";
        }

        double netGain = totalYearlySavings - subscriptionPricePerYear;
        if (netGain >= 0) {
            return String.format("Estimated yearly savings (€%.2f) exceed the €%.2f subscription by €%.2f (ROI %.1f%%).",
                    totalYearlySavings, subscriptionPricePerYear, netGain, roiPercentage);
        }

        return String.format("Estimated yearly savings (€%.2f) do not cover the €%.2f subscription (ROI %.1f%%).",
                totalYearlySavings, subscriptionPricePerYear, roiPercentage);
    }
}
