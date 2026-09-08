package com.gitanjali.devassist.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.AnalysisResponse;
import com.gitanjali.devassist.entity.Analysis;
import com.gitanjali.devassist.repository.AnalysisRepository;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final AIService aiService;
    private final AnalysisRepository analysisRepository;

    public AnalysisServiceImpl(
            AIService aiService,
            AnalysisRepository analysisRepository) {

        this.aiService = aiService;
        this.analysisRepository = analysisRepository;
    }

    @Override
    public AnalysisResponse analyzeError(AnalysisRequest request) {

        // Get AI response
        AnalysisResponse aiResponse =
                aiService.analyzeError(request);

        // Create Analysis entity
        Analysis analysis = new Analysis();

        analysis.setErrorInput(request.getErrorInput());
        analysis.setLanguage(request.getLanguage());
        analysis.setProblem(aiResponse.getProblem());
        analysis.setSolution(aiResponse.getSolution());
        analysis.setSuggestedCode(aiResponse.getSuggestedCode());
        analysis.setCreatedAt(LocalDateTime.now());

        // Save to PostgreSQL
        Analysis savedAnalysis =
                analysisRepository.save(analysis);

        // Add database values to response
        aiResponse.setId(savedAnalysis.getId());
        aiResponse.setCreatedAt(savedAnalysis.getCreatedAt());

        return aiResponse;
    }
}