package com.core.analyzer.similarity;

import java.util.List;

public class L2Similarity implements SimilarityStrategy {

    @Override
    public double calculate(int[] target, List<Integer> candidate) {
        double sum = 0;
        for (int i = 0; i < target.length; i++) {
            sum += Math.pow(target[i] - candidate.get(i), 2);
        }
        return Math.sqrt(sum); // L2 거리 = 제곱합의 루트
    }
}
