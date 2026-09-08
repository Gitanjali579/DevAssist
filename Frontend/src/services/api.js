const API_BASE_URL = "http://localhost:8080/api";

// Analyze error
export const analyzeError = async (data) => {
    const response = await fetch(`${API_BASE_URL}/analysis`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    const result = await response.json();

    if (!response.ok) {
        throw new Error(result.message || "Failed to analyze error");
    }

    return result;
};


// Generate test cases
export const generateTestCases = async (data) => {
    const response = await fetch(`${API_BASE_URL}/analysis/test-cases`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    const result = await response.json();

    if (!response.ok) {
        throw new Error(result.message || "Failed to generate test cases");
    }

    return result;
};


// Get history
export const getHistory = async () => {
    const response = await fetch(`${API_BASE_URL}/history`);

    const result = await response.json();

    if (!response.ok) {
        throw new Error(result.message || "Failed to fetch history");
    }

    return result;
};


// Get single history
export const getHistoryById = async (id) => {
    const response = await fetch(`${API_BASE_URL}/history/${id}`);

    const result = await response.json();

    if (!response.ok) {
        throw new Error(result.message || "Failed to fetch analysis");
    }

    return result;
};


// Delete history
export const deleteHistory = async (id) => {
    const response = await fetch(`${API_BASE_URL}/history/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        let message = "Failed to delete history";

        try {
            const result = await response.json();
            message = result.message || message;
        } catch {
            // No JSON response
        }

        throw new Error(message);
    }

    return true;
};