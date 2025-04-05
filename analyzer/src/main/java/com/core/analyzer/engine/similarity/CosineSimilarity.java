package com.core.analyzer.engine.similarity;

import java.util.List;

public class CosineSimilarity implements SimilarityStrategy {
    private static final double ZERO_PENALTY_THRESHOLD = 0.6; // 60% 이상 0일 경우 패널티 부여
    private static final double ZERO_PENALTY_WEIGHT = 0.5; // 패널티 가중치

    @Override
    public double calculate(List<Double> target, List<Double> candidate) {
        double dot = 0;
        double magTarget = 0;
        double magCandidate = 0;

        for (int i = 0; i < target.size(); i++) {
            double t = target.get(i);
            double c = candidate.get(i);
            dot += t * c;
            magTarget += t * t;
            magCandidate += c * c;
        }

        if (magTarget == 0 || magCandidate == 0) return 0;

        double cosineScore = dot / (Math.sqrt(magTarget) * Math.sqrt(magCandidate));

        // zero 비율 기반 penalty 부여
        long zeroCount = candidate.stream().filter(d -> d == 0.0).count();
        double zeroRatio = zeroCount / (double) candidate.size();
        double penalty = zeroRatio > ZERO_PENALTY_THRESHOLD ? (zeroRatio * ZERO_PENALTY_WEIGHT) : 0;

        return cosineScore - penalty; // Cosine score는 높을수록 유사, penalty는 감소로 적용
    }
}

