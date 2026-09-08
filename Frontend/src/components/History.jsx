function History({
    history,
    onDelete,
    loading
}) {

    return (
        <section className="history-card">

            <div className="section-title">

                <span className="section-icon">
                    🕘
                </span>

                <div>
                    <h2>Analysis History</h2>

                    <p>
                        Your previous debugging analyses.
                    </p>
                </div>

            </div>


            {loading && (
                <p className="history-message">
                    Loading history...
                </p>
            )}


            {!loading && history.length === 0 && (
                <div className="empty-history">
                    <span>📂</span>

                    <p>
                        No analysis history yet.
                    </p>

                    <small>
                        Analyze an error to see it here.
                    </small>
                </div>
            )}


            {!loading && history.length > 0 && (

                <div className="history-list">

                    {history.map((item) => (

                        <div
                            className="history-item"
                            key={item.id}
                        >

                            <div className="history-info">

                                <div className="history-top">

                                    <span className="history-language">
                                        {item.language}
                                    </span>

                                    <span className="history-date">
                                        {item.createdAt
                                            ? new Date(item.createdAt).toLocaleString()
                                            : ""}
                                    </span>

                                </div>


                                <h3>
                                    {item.errorInput}
                                </h3>

                            </div>


                            <button
                                className="delete-button"
                                onClick={() => onDelete(item.id)}
                            >
                                🗑️
                            </button>

                        </div>

                    ))}

                </div>

            )}

        </section>
    );
}

export default History;