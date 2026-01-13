import { useEffect, useState } from 'react';
import { WordService } from './services/WordService';
import Flashcard from './components/Flashcard';

function App() {
    const [words, setWords] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null); // Added error state

    useEffect(() => {
        loadWords();
    }, []);

    const loadWords = async () => {
        setLoading(true);
        setError(null);

        try {
            const data = await WordService.findAllWords();
            setWords(data);
        } catch (err) {
            setError("Failed to load words. Check if Backend is running.");
        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="min-h-screen flex flex-col bg-slate-50">
            {/* HEADER */}
            <header className="bg-white shadow-sm sticky top-0 z-10">
                <div className="max-w-6xl mx-auto px-6 py-4 flex justify-between items-center">
                    <div className="flex items-center gap-2">
                        <div className="w-8 h-8 bg-yellow-400 rounded-lg flex items-center justify-center font-bold text-yellow-900">
                            De
                        </div>
                        <h1 className="text-xl font-bold text-gray-800 tracking-tight">
                            DerDieDas <span className="text-blue-600">Trainer</span>
                        </h1>
                    </div>
                    <button
                        onClick={loadWords}
                        className="text-sm font-medium text-gray-500 hover:text-blue-600 transition"
                    >
                        Refresh
                    </button>
                </div>
            </header>

            {/* MAIN CONTENT */}
            <main className="flex-grow p-8">
                <div className="max-w-6xl mx-auto">

                    {loading ? (
                        <div className="text-center py-20">
                            <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto mb-4"></div>
                            <p className="text-gray-500">Loading your vocabulary...</p>
                        </div>
                    ) : error ? (
                        <div className="text-center py-20 text-red-500">
                            <p>{error}</p>
                            <button onClick={loadWords} className="mt-4 underline">Try Again</button>
                        </div>
                    ) : words.length === 0 ? (
                        <div className="text-center py-20 bg-white rounded-xl shadow-sm border border-gray-100">
                            <h2 className="text-2xl font-bold text-gray-700 mb-2">No words found</h2>
                            <p className="text-gray-500">Your database is empty.</p>
                        </div>
                    ) : (
                        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8 place-items-center">
                            {words.map((word) => (
                                <Flashcard key={word.id} wordData={word} />
                            ))}
                        </div>
                    )}
                </div>
            </main>

            <footer className="bg-white border-t border-gray-200 py-6 text-center text-gray-400 text-sm">
                <p>© 2026 DerDieDas Trainer • Built with Spring Boot & React</p>
            </footer>
        </div>
    );
}

export default App;