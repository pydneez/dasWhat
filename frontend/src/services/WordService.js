const API_URL = "http://localhost:8080/api/v1/words";

export const WordService = {
  // RENAMED from getAllWords to findAllWords
  findAllWords: async () => {
    try {
      const response = await fetch(API_URL);
      if (!response.ok) {
        throw new Error(`Server error: ${response.status}`);
      }
      return await response.json();
    } catch (error) {
      console.error("Failed to fetch words:", error);
      throw error; // Re-throw so App.jsx knows it failed
    }
  }
};