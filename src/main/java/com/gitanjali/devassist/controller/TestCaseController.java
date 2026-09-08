package com.gitanjali.devassist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.TestCaseResponse;
import com.gitanjali.devassist.service.TestCaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/analysis/test-cases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @PostMapping
    public ResponseEntity<TestCaseResponse> generateTestCases(
            @Valid @RequestBody AnalysisRequest request) {

        TestCaseResponse response =
                testCaseService.generateTestCases(request);

        return ResponseEntity.ok(response);
    }
}