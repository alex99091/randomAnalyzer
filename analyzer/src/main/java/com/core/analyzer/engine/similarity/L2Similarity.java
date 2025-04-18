package com.core.analyzer.engine.similarity;

import java.util.List;



public class L2Similarity implements SimilarityStrategy {
    @Override
    public double calculate(List<Double> target, List<Double> candidate) {
        double sumSq = 0;
        for (int i = 0; i < target.size(); i++) {
            double diff = target.get(i) - candidate.get(i);
            sumSq += diff * diff;
        }

        // 정규화: 최대 제곱합은 각 항목 (5^2) * 길이
        double maxDiffSq = target.size() * 25.0;
        double score = 1.0 - (sumSq / maxDiffSq);
        return Math.max(0, Math.min(1, score));  // 0 ~ 1 사이 보정
    }
}
