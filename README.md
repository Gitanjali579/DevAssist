# DevAssist 🤖

AI-Powered Code Debugging & Testing Assistant

## Problem
Developers often spend a lot of time understanding errors,
exceptions and stack traces and finding suitable fixes.

## Solution
DevAssist uses AI to analyze programming errors, explain the
problem, suggest solutions and generate test cases.

## Features
- AI-powered error analysis
- Problem explanation
- Solution suggestions
- Suggested code
- AI-generated test cases
- Analysis history
- PostgreSQL database
- React frontend
- Spring Boot REST API

## Tech Stack
- React
- Vite
- Java
- Spring Boot
- PostgreSQL
- Google Gemini API

## Architecture

React Frontend
      ↓
Spring Boot REST API
      ↓
Google Gemini AI
      ↓
PostgreSQL

## API Endpoints

POST /api/analysis
POST /api/analysis/test-cases
GET /api/history
GET /api/history/{id}
DELETE /api/history/{id}

## How to Run

### Backend

Configure PostgreSQL and Gemini API key in
application.properties.

Then run the Spring Boot application.

Backend:
http://localhost:8080

### Frontend

```bash
cd frontend
npm install
npm run dev