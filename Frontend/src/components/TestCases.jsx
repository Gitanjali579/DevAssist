function TestCases({ data }) {

    if (!data) {
        return null;
    }

    const testCaseText =
        data.testCases && data.testCases.length > 0
            ? data.testCases.join("\n\n")
            : "No test cases generated.";

    return (
        <section className="result-card">

            <div className="result-header">

                <div>
                    <h2>🧪 AI Generated Test Cases</h2>

                    <p>
                        Test cases generated for your code.
                    </p>
                </div>

                <span className="language-badge">
                    {data.language}
                </span>

            </div>


            <div className="test-case-container">

                <pre>
                    {testCaseText}
                </pre>

            </div>

        </section>
    );
}

export default TestCases;