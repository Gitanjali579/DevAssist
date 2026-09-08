function AnalysisResult({ analysis }) {

    if (!analysis) {
        return null;
    }

    return (
        <section className="result-card">

            <div className="result-header">

                <div>
                    <h2>🤖 AI Analysis</h2>

                    <p>
                        DevAssist analyzed your error and generated a solution.
                    </p>
                </div>

                <span className="language-badge">
                    {analysis.language}
                </span>

            </div>


            <div className="result-block">

                <h3>🔴 Problem</h3>

                <p>
                    {analysis.problem}
                </p>

            </div>


            <div className="result-block">

                <h3>💡 Solution</h3>

                <p>
                    {analysis.solution}
                </p>

            </div>


            <div className="result-block">

                <h3>💻 Suggested Code</h3>

                <pre>
                    <code>
                        {analysis.suggestedCode || "No code suggestion available."}
                    </code>
                </pre>

            </div>


            {analysis.createdAt && (
                <div className="analysis-meta">
                    Analysis ID: {analysis.id} &nbsp; | &nbsp;
                    {new Date(analysis.createdAt).toLocaleString()}
                </div>
            )}

        </section>
    );
}

export default AnalysisResult;