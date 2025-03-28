package com.core.analyzer.domain.draw;

public interface DrawResultService {
    void save(DrawResult drawResult);
    DrawResult findDrawResult(int idx);
}
