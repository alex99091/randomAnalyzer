package com.core.analyzer.engine.similarity;

import java.util.List;

public interface SimilarityStrategy {
    double calculate(List<Double> target, List<Double> candidate);
}

