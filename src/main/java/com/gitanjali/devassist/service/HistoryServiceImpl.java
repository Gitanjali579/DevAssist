package com.gitanjali.devassist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gitanjali.devassist.entity.Analysis;
import com.gitanjali.devassist.repository.AnalysisRepository;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final AnalysisRepository analysisRepository;

    public HistoryServiceImpl(AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }

    @Override
    public List<Analysis> getAllHistory() {
        return analysisRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Analysis getHistoryById(Long id) {
        return analysisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analysis not found with id: " + id));
    }

    @Override
    public void deleteHistory(Long id) {
        if (!analysisRepository.existsById(id)) {
            throw new RuntimeException("Analysis not found with id: " + id);
        }

        analysisRepository.deleteById(id);
    }
}