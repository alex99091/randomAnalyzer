package com.core.analyzer.drawResult;

import java.util.HashMap;
import java.util.Map;

public class MemoryDrawResultRepository implements DrawResultRepository {

    private static Map<Integer, DrawResult> store = new HashMap<>();

    @Override
    public void save(DrawResult drawResult) {
        store.put(drawResult.getIdx(), drawResult);
    }

    @Override
    public DrawResult findById(int idx) {
        return store.get(idx);
    }
}
