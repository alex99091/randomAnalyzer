package com.core.analyzer.engine.similarity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HammingJaccardSimilarity implements SimilarityStrategy {
    @Override
    public double calculate(List<Double> target, List<Double> candidate) {
        if (target.size() != candidate.size()) return 0;

        int matchCount = 0;
        for (int i = 0; i < target.size(); i++) {
            if (target.get(i).equals(candidate.get(i))) {
                matchCount++;
            }
        }
        double hammingScore = matchCount / (double) target.size(); // 0.0 ~ 1.0

        Set<Double> union = new HashSet<>(target);
        union.addAll(candidate);

        Set<Double> intersection = new HashSet<>(target);
        intersection.retainAll(candidate);

        double jaccardScore = union.isEmpty() ? 0 : (intersection.size() / (double) union.size());

        return (hammingScore + jaccardScore) / 2.0;
    }
}
