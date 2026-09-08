package com.gitanjali.devassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.AnalysisResponse;
import com.gitanjali.devassist.service.AnalysisService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping
    public ResponseEntity<AnalysisResponse> analyzeError(
            @Valid @RequestBody AnalysisRequest request) {

        AnalysisResponse response =
                analysisService.analyzeError(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}