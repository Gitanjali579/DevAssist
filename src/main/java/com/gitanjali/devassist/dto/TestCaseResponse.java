package com.gitanjali.devassist.dto;

import java.util.List;

public class TestCaseResponse {

    private String language;
    private List<String> testCases;

    public TestCaseResponse() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<String> testCases) {
        this.testCases = testCases;
    }
}