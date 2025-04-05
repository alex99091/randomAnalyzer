package com.core.analyzer.engine.similarity;

import java.util.List;


public class L2Similarity implements SimilarityStrategy {
    private static final double ZERO_PENALTY_THRESHOLD = 0.6; // 60% 이상 0일 경우 패널티 부여
    private static final double ZERO_PENALTY_WEIGHT = 0.5; // 패널티 가중치

    @Override
    public double calculate(List<Double> target, List<Double> candidate) {
        double sum = 0;
        for (int i = 0; i < target.size(); i++) {
            sum += Math.pow(target.get(i) - candidate.get(i), 2);
        }

        double distance = Math.sqrt(sum);

        // zero 비율 기반 penalty 부여
        long zeroCount = candidate.stream().filter(d -> d == 0.0).count();
        double zeroRatio = zeroCount / (double) candidate.size();
        double penalty = zeroRatio > ZERO_PENALTY_THRESHOLD ? (zeroRatio * ZERO_PENALTY_WEIGHT) : 0;

        return distance + penalty;
    }
}
