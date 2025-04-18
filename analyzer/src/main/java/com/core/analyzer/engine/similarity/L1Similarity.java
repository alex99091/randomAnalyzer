package com.core.analyzer.engine.similarity;

import java.util.List;


public class L1Similarity implements SimilarityStrategy {
    @Override
    public double calculate(List<Double> target, List<Double> candidate) {
        double sum = 0;
        for (int i = 0; i < target.size(); i++) {
            sum += Math.abs(target.get(i) - candidate.get(i));
        }

        // 정규화: 최대 차이는 (가정) 각 항목 최대 5 범위 → 총 길이 * 5
        double maxDiff = target.size() * 5.0;
        double score = 1.0 - (sum / maxDiff);
        return Math.max(0, Math.min(1, score));  // 0 ~ 1 사이 보정
    }
}

