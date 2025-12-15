package com.smartroad.demo;

public class DemoScenarioNotFoundException extends RuntimeException {

    public DemoScenarioNotFoundException(String scenarioId) {
        super("Unknown demo scenario: " + scenarioId);
    }
}
