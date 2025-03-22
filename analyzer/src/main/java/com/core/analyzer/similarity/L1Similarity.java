package com.core.analyzer.similarity;

import java.util.List;

public class L1Similarity implements SimilarityStrategy {
    @Override
    public double calculate(int[] target, List<Integer> candidate) {
        int sum = 0;
        for (int i = 0; i < target.length; i++) {
            sum += Math.abs(target[i] - candidate.get(i));
        }
        return sum;
    }
}

