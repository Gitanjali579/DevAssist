package com.gitanjali.devassist.service;

import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.AnalysisResponse;

public interface AnalysisService {

    AnalysisResponse analyzeError(AnalysisRequest request);
}