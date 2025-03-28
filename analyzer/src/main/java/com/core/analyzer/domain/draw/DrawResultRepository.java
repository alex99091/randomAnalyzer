package com.core.analyzer.domain.draw;

public interface DrawResultRepository {
    void save(DrawResult drawResult);
    DrawResult findById(int idx);
}
