package com.core.analyzer.similarity;

import java.util.List;

public interface SimilarityStrategy {
    double calculate(int[] target, List<Integer> candidate);
}

