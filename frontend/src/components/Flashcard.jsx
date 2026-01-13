import { useState } from 'react';

const Flashcard = ({ wordData }) => {
  const [isFlipped, setIsFlipped] = useState(false);

  // Dynamic colors based on the article
  const getTheme = (article) => {
    switch (article) {
      case 'DER': return 'bg-blue-50 text-blue-900 border-blue-200';
      case 'DIE': return 'bg-red-50 text-red-900 border-red-200';
      case 'DAS': return 'bg-green-50 text-green-900 border-green-200';
      default: return 'bg-white text-gray-900 border-gray-200';
    }
  };

  const getBadgeColor = (article) => {
    switch (article) {
      case 'DER': return 'bg-blue-600 text-white';
      case 'DIE': return 'bg-red-600 text-white';
      case 'DAS': return 'bg-green-600 text-white';
      default: return 'bg-gray-600 text-white';
    }
  };

  return (
      <div
          className="group w-full max-w-xs h-64 perspective-1000 cursor-pointer"
          onClick={() => setIsFlipped(!isFlipped)}
      >
        <div className={`
        relative w-full h-full duration-500 preserve-3d transition-transform
        ${isFlipped ? 'rotate-y-180' : ''}
      `}>

          {/* === FRONT SIDE (Question) === */}
          <div className={`
          absolute w-full h-full backface-hidden rounded-2xl shadow-lg border-2
          flex flex-col items-center justify-center bg-white hover:shadow-xl transition-shadow
        `}>
          <span className="absolute top-4 right-4 text-xs font-bold text-gray-400 uppercase tracking-widest">
            {wordData.category}
          </span>

            <h2 className="text-4xl font-extrabold text-gray-800">
              {wordData.word}
            </h2>

            <p className="absolute bottom-4 text-sm text-gray-400 font-medium">
              Tap to reveal
            </p>
          </div>

          {/* === BACK SIDE (Answer) === */}
          <div className={`
          absolute w-full h-full backface-hidden rotate-y-180 rounded-2xl shadow-lg border-2
          flex flex-col items-center justify-center p-6 text-center
          ${getTheme(wordData.article)}
        `}>
            <div className="flex items-center gap-2 mb-2">
            <span className={`px-3 py-1 rounded-full text-sm font-bold shadow-sm ${getBadgeColor(wordData.article)}`}>
              {wordData.article}
            </span>
              <span className="text-2xl font-bold">{wordData.word}</span>
            </div>

            <p className="text-lg italic opacity-80 mb-4">
              "{wordData.translation}"
            </p>

            <div className="absolute bottom-4 flex gap-2">
             <span className="px-2 py-1 text-xs font-bold border rounded bg-white/50">
               Level: {wordData.proficiencyLevel}
             </span>
            </div>
          </div>
        </div>

        {/* Tailwind Utility for 3D Support (Add this if the flip looks flat) */}
        <style>{`
        .perspective-1000 { perspective: 1000px; }
        .preserve-3d { transform-style: preserve-3d; }
        .backface-hidden { backface-visibility: hidden; }
        .rotate-y-180 { transform: rotateY(180deg); }
      `}</style>
      </div>
  );
};

export default Flashcard;