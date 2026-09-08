package com.gitanjali.devassist.service;


import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.AnalysisResponse;
import com.gitanjali.devassist.dto.TestCaseResponse;

public interface AIService {

    AnalysisResponse analyzeError(AnalysisRequest request);

    TestCaseResponse generateTestCases(AnalysisRequest request);
}
