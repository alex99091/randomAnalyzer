package com.core.analyzer.drawResult;

public interface DrawResultRepository {
    void save(DrawResult drawResult);
    DrawResult findById(int idx);
}
