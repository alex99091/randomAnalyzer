package com.core.analyzer.engine.similarity;

import java.util.List;

public class L2Similarity implements SimilarityStrategy {
    @Override
    public double calculate(List<Double> target, List<Double> candidate) {
        double sum = 0;
        for (int i = 0; i < target.size(); i++) {
            sum += Math.pow(target.get(i) - candidate.get(i), 2);
        }
        return Math.sqrt(sum);
    }
}
