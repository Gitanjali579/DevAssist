package com.gitanjali.devassist.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.gitanjali.devassist.dto.AnalysisRequest;
import com.gitanjali.devassist.dto.AnalysisResponse;
import com.gitanjali.devassist.dto.TestCaseResponse;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class AIServiceImpl implements AIService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.model}")
    private String model;

    public AIServiceImpl(
            RestClient restClient,
            ObjectMapper objectMapper) {

        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public AnalysisResponse analyzeError(AnalysisRequest request) {

    	String prompt = """
    	        You are DevAssist, an expert software testing assistant.

    	        Analyze the following programming error and generate exactly 5 useful test cases.

    	        Programming Language:
    	        %s

    	        Error:
    	        %s

    	        Return exactly 5 test cases.
    	        Number them from 1 to 5.
    	        Keep each test case concise.
    	        Include normal, invalid, null/empty, boundary, and edge-case testing where applicable.
    	        """.formatted(
    	                request.getLanguage(),
    	                request.getErrorInput()
    	        );

        String aiResponse = callGemini(prompt);

        String problem = extractSection(aiResponse, "PROBLEM:", "WHY:");
        String solution = extractSection(aiResponse, "SOLUTION:", "SUGGESTED CODE:");
        String suggestedCode = extractSection(aiResponse, "SUGGESTED CODE:", null);

        AnalysisResponse response = new AnalysisResponse();

        response.setErrorInput(request.getErrorInput());
        response.setLanguage(request.getLanguage());
        response.setProblem(problem);
        response.setSolution(solution);
        response.setSuggestedCode(suggestedCode);

        return response;
    }

    @Override
    public TestCaseResponse generateTestCases(AnalysisRequest request) {

        String prompt = """
                You are a software testing expert.

                Generate useful test cases for the following code/error.

                Programming Language:
                %s

                Input:
                %s

                Return 5 concise test cases.
                """.formatted(
                        request.getLanguage(),
                        request.getErrorInput()
                );

        String aiResponse = callGemini(prompt);

        TestCaseResponse response = new TestCaseResponse();

        response.setLanguage(request.getLanguage());
        response.setTestCases(
                Collections.singletonList(aiResponse)
        );

        return response;
    }

    private String callGemini(String prompt) {

        String endpoint =
                "/v1beta/models/" + model + ":generateContent";

        String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": %s
                        }
                      ]
                    }
                  ]
                }
                """.formatted(toJsonString(prompt));

        String response = restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("key", apiKey)
                        .build())
                .header("Content-Type", "application/json")
                .body(requestBody)
                .retrieve()
                .body(String.class);

        try {
            JsonNode root = objectMapper.readTree(response);

            return root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to read AI response: " + e.getMessage()
            );
        }
    }

    private String toJsonString(String text) {

        try {
            return objectMapper.writeValueAsString(text);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to create AI request: " + e.getMessage()
            );
        }
    }

    private String extractSection(
            String text,
            String start,
            String end) {

        int startIndex = text.indexOf(start);

        if (startIndex == -1) {
            return text;
        }

        startIndex += start.length();

        int endIndex;

        if (end == null) {
            endIndex = text.length();
        } else {
            endIndex = text.indexOf(end, startIndex);

            if (endIndex == -1) {
                endIndex = text.length();
            }
        }

        return text.substring(startIndex, endIndex).trim();
    }
}