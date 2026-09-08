import { useEffect, useState } from "react";

import ErrorInput from "./components/ErrorInput";
import AnalysisResult from "./components/AnalysisResult";
import TestCases from "./components/TestCases";
import History from "./components/History";

import {
    analyzeError,
    generateTestCases,
    getHistory,
    deleteHistory
} from "./services/api";

import "./App.css";


function App() {

    const [analysis, setAnalysis] = useState(null);

    const [testCases, setTestCases] = useState(null);

    const [history, setHistory] = useState([]);

    const [loading, setLoading] = useState(false);

    const [historyLoading, setHistoryLoading] = useState(true);

    const [error, setError] = useState("");


    // Load history when application starts
    useEffect(() => {
        loadHistory();
    }, []);


    const loadHistory = async () => {

        try {

            setHistoryLoading(true);

            const data = await getHistory();

            setHistory(data);

        } catch (err) {

            console.error("History error:", err);

        } finally {

            setHistoryLoading(false);
        }
    };


    // Analyze error
    const handleAnalyze = async (data) => {

        try {

            setLoading(true);
            setError("");

            setAnalysis(null);

            const result = await analyzeError(data);

            setAnalysis(result);

            // Refresh history
            await loadHistory();

        } catch (err) {

            console.error("Analysis error:", err);

            setError(
                err.message || "Something went wrong while analyzing."
            );

        } finally {

            setLoading(false);
        }
    };


    // Generate test cases
    const handleGenerateTestCases = async (data) => {

        try {

            setLoading(true);
            setError("");

            setTestCases(null);

            const result = await generateTestCases(data);

            setTestCases(result);

        } catch (err) {

            console.error("Test case error:", err);

            setError(
                err.message ||
                "Something went wrong while generating test cases."
            );

        } finally {

            setLoading(false);
        }
    };


    // Delete history
    const handleDelete = async (id) => {

        const confirmDelete =
            window.confirm(
                "Are you sure you want to delete this analysis?"
            );

        if (!confirmDelete) {
            return;
        }


        try {

            await deleteHistory(id);

            setHistory((previousHistory) =>
                previousHistory.filter(
                    (item) => item.id !== id
                )
            );

        } catch (err) {

            setError(
                err.message ||
                "Failed to delete history."
            );

        }
    };


    return (

        <div className="app">

            {/* Header */}

            <header className="app-header">

                <div className="logo">

                    <div className="logo-icon">
                        🤖
                    </div>

                    <div>
                        <h1>DevAssist</h1>

                        <p>
                            AI-Powered Code Debugging & Testing Assistant
                        </p>
                    </div>

                </div>

            </header>


            {/* Main */}

            <main className="container">


                {/* Hero */}

                <section className="hero">

                    <h2>
                        Debug smarter. Code better.
                    </h2>

                    <p>
                        Paste your error and let AI explain the problem,
                        suggest a solution, and generate test cases.
                    </p>

                </section>


                {/* Input */}

                <ErrorInput
                    onAnalyze={handleAnalyze}
                    onGenerateTestCases={handleGenerateTestCases}
                    loading={loading}
                />


                {/* Error */}

                {error && (

                    <div className="error-message">

                        ⚠️ {error}

                    </div>

                )}


                {/* Loading */}

                {loading && (

                    <div className="loading-card">

                        <div className="spinner"></div>

                        <p>
                            AI is processing your request...
                        </p>

                    </div>

                )}


                {/* Analysis */}

                {!loading && analysis && (

                    <AnalysisResult
                        analysis={analysis}
                    />

                )}


                {/* Test Cases */}

                {!loading && testCases && (

                    <TestCases
                        data={testCases}
                    />

                )}


                {/* History */}

                <History
                    history={history}
                    onDelete={handleDelete}
                    loading={historyLoading}
                />


            </main>


            {/* Footer */}

            <footer className="footer">

                <p>
                    DevAssist • AI-Powered Developer Productivity Tool
                </p>

            </footer>

        </div>
    );
}

export default App;