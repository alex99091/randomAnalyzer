package com.core.analyzer.engine.similarity;

import java.util.List;

public class CosineSimilarity implements SimilarityStrategy {
    @Override
    public double calculate(List<Double> target, List<Double> candidate) {
        double dot = 0;
        double normTarget = 0;
        double normCandidate = 0;

        for (int i = 0; i < target.size(); i++) {
            double t = target.get(i);
            double c = candidate.get(i);
            dot += t * c;
            normTarget += t * t;
            normCandidate += c * c;
        }

        if (normTarget == 0 || normCandidate == 0) return 0;

        double cosine = dot / (Math.sqrt(normTarget) * Math.sqrt(normCandidate));

        // 💡 정규화 보정: 음수 방지 및 비교 가능성 확보
        return (cosine + 1.0) / 2.0;  // 0.0 ~ 1.0 스케일
    }
}

