package com.core.analyzer.similarity;

import com.core.analyzer.dto.DrawWithBoxPattern;

import java.util.List;

public class SimilarityAnalyzer {
    private final SimilarityStrategy strategy;

    public SimilarityAnalyzer(SimilarityStrategy strategy) {
        this.strategy = strategy;
    }

    public DrawWithBoxPattern findMostSimilar(int[] target, List<DrawWithBoxPattern> list) {
        DrawWithBoxPattern best = null;
        double bestScore = strategy instanceof CosineSimilarity ? -1 : Double.MAX_VALUE;

        for (DrawWithBoxPattern d : list) {
            double score = strategy.calculate(target, d.getBoxPattern());
            boolean isBetter = (strategy instanceof CosineSimilarity) ? score > bestScore : score < bestScore;

            if (isBetter) {
                bestScore = score;
                best = d;
            }
        }

        return best;
    }
}

