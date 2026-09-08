import { useState } from "react";

function ErrorInput({
    onAnalyze,
    onGenerateTestCases,
    loading
}) {

    const [language, setLanguage] = useState("Java");
    const [errorInput, setErrorInput] = useState("");

    const handleAnalyze = () => {

        if (!errorInput.trim()) {
            alert("Please enter an error or stack trace.");
            return;
        }

        onAnalyze({
            errorInput,
            language
        });
    };

    const handleTestCases = () => {

        if (!errorInput.trim()) {
            alert("Please enter an error or stack trace.");
            return;
        }

        onGenerateTestCases({
            errorInput,
            language
        });
    };

    return (
        <section className="input-card">

            <div className="section-title">
                <span className="section-icon">🐞</span>

                <div>
                    <h2>Debug Your Code</h2>

                    <p>
                        Paste your error, exception, or stack trace below.
                    </p>
                </div>
            </div>


            <label htmlFor="language">
                Programming Language
            </label>

            <select
                id="language"
                value={language}
                onChange={(e) => setLanguage(e.target.value)}
                disabled={loading}
            >
                <option value="Java">Java</option>
                <option value="JavaScript">JavaScript</option>
                <option value="Python">Python</option>
                <option value="SQL">SQL</option>
                <option value="C++">C++</option>
            </select>


            <label htmlFor="errorInput">
                Error / Exception / Stack Trace
            </label>

            <textarea
                id="errorInput"
                value={errorInput}
                onChange={(e) => setErrorInput(e.target.value)}
                placeholder={`Paste your error here...

Example:
java.lang.NullPointerException
    at com.example.UserService.getUser(UserService.java:10)`}
                rows="10"
                disabled={loading}
            />


            <div className="action-buttons">

                <button
                    className="primary-button"
                    onClick={handleAnalyze}
                    disabled={loading}
                >
                    {loading ? "Analyzing..." : "🔍 Analyze Error"}
                </button>


                <button
                    className="secondary-button"
                    onClick={handleTestCases}
                    disabled={loading}
                >
                    {loading ? "Generating..." : "🧪 Generate Test Cases"}
                </button>

            </div>

        </section>
    );
}

export default ErrorInput;