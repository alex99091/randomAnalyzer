package com.core.analyzer.similarity;

import java.util.List;

public class CosineSimilarity implements SimilarityStrategy {

    @Override
    public double calculate(int[] target, List<Integer> candidate) {
        double dot = 0;
        double magTarget = 0;
        double magCandidate = 0;

        for (int i = 0; i < target.length; i++) {
            dot += target[i] * candidate.get(i);
            magTarget += target[i] * target[i];
            magCandidate += candidate.get(i) * candidate.get(i);
        }

        if (magTarget == 0 || magCandidate == 0) return 0; // 벡터 중 하나가 0이면 유사도 0

        return dot / (Math.sqrt(magTarget) * Math.sqrt(magCandidate));
    }
}
