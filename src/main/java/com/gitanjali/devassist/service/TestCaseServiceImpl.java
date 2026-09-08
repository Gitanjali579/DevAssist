package com.gitanjali.devassist.service;

import org.springframework.stereotype.Service;

import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.TestCaseResponse;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    private final AIService aiService;

    public TestCaseServiceImpl(AIService aiService) {
        this.aiService = aiService;
    }

    @Override
    public TestCaseResponse generateTestCases(AnalysisRequest request) {

        return aiService.generateTestCases(request);
    }
}