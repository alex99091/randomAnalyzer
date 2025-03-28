package com.core.analyzer.engine.similarity;

import java.util.List;

public class CosineSimilarity implements SimilarityStrategy {
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

        return dot / (Math.sqrt(magTarget) * Math.sqrt(magCandidate));
    }
}

