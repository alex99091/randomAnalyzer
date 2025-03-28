package com.core.analyzer.engine.similarity;

import com.core.analyzer.domain.pattern.DrawWithBoxPattern;

import java.util.ArrayList;
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
            // 확장된 21차원 벡터로 비교 (boxPattern + boxMeans + boxStds)
            List<Double> fullCandidateVector = new ArrayList<>();

            // 박스 카운트 (int → double)
            for (int count : d.getBoxPattern()) {
                fullCandidateVector.add((double) count);
            }

            // 평균 + 표준편차 추가
            double[] means = d.getBoxMeans();
            double[] stds = d.getBoxStds();

            for (int i = 1; i <= 7; i++) {
                fullCandidateVector.add(means[i]);
            }
            for (int i = 1; i <= 7; i++) {
                fullCandidateVector.add(stds[i]);
            }

            // 타겟도 확장
            List<Double> fullTargetVector = new ArrayList<>();
            for (int v : target) fullTargetVector.add((double) v);
            for (int i = 1; i <= 7; i++) fullTargetVector.add(0.0); // mean placeholder
            for (int i = 1; i <= 7; i++) fullTargetVector.add(0.0); // std placeholder

            double score = strategy.calculate(fullTargetVector, fullCandidateVector);
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
