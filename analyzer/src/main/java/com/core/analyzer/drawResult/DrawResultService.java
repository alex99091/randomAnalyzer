package com.core.analyzer.drawResult;

public interface DrawResultService {
    void save(DrawResult drawResult);
    DrawResult findDrawResult(int idx);
}
