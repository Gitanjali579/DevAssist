package com.gitanjali.devassist.dto;

import jakarta.validation.constraints.NotBlank;

public class AnalysisRequest {

    @NotBlank(message = "Error input is required")
    private String errorInput;

    @NotBlank(message = "Programming language is required")
    private String language;

    public AnalysisRequest() {
    }

    public AnalysisRequest(String errorInput, String language) {
        this.errorInput = errorInput;
        this.language = language;
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
}