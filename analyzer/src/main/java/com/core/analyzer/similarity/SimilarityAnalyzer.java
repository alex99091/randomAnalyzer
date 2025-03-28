package com.core.analyzer.similarity;

import com.core.analyzer.dto.DrawWithBoxPattern;

import java.util.List;

public class SimilarityAnalyzer {
    private final SimilarityStrategy strategy;

    public SimilarityAnalyzer(SimilarityStrategy strategy) {
        this.strategy = strategy;
    }

    // 점수와 함께 반환
    public Result findMostSimilarWithScore(int[] target, List<DrawWithBoxPattern> list) {
        DrawWithBoxPattern best = null;
        double bestScore = (strategy instanceof CosineSimilarity) ? -1 : Double.MAX_VALUE;

        for (DrawWithBoxPattern d : list) {
            double score = strategy.calculate(target, d.getBoxPattern());
            boolean isBetter = (strategy instanceof CosineSimilarity) ? score > bestScore : score < bestScore;

            if (isBetter) {
                bestScore = score;
                best = d;
            }
        }

        return new Result(best, bestScore);
    }

    // 기존 방식도 유지
    public DrawWithBoxPattern findMostSimilar(int[] target, List<DrawWithBoxPattern> list) {
        return findMostSimilarWithScore(target, list).pattern();
    }

    // 내부 record for result
    public record Result(DrawWithBoxPattern pattern, double score) {}
}
