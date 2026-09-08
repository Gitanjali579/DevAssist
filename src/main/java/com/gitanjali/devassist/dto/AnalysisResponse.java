package com.gitanjali.devassist.dto;

import java.time.LocalDateTime;

public class AnalysisResponse {

    private Long id;
    private String errorInput;
    private String language;
    private String problem;
    private String solution;
    private String suggestedCode;
    private LocalDateTime createdAt;

    public AnalysisResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorInput() {
        return errorInput;
    }

    public void setErrorInput(String errorInput) {
        this.errorInput = errorInput;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSuggestedCode() {
        return suggestedCode;
    }

    public void setSuggestedCode(String suggestedCode) {
        this.suggestedCode = suggestedCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}