package com.gitanjali.devassist.service;

import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.TestCaseResponse;

public interface TestCaseService {

    TestCaseResponse generateTestCases(AnalysisRequest request);
}