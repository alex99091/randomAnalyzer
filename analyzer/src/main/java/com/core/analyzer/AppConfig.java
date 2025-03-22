package com.core.analyzer;

import com.core.analyzer.analyze.FixDataPolicy;
import com.core.analyzer.drawResult.DrawResultService;
import com.core.analyzer.drawResult.DrawResultServiceImpl;
import com.core.analyzer.drawResult.MemoryDrawResultRepository;

public class AppConfig {

    public DrawResultService drawResultService() {
        return new DrawResultServiceImpl(drawResultRepository());
    }

    private static MemoryDrawResultRepository drawResultRepository() {
        return new MemoryDrawResultRepository();
    }

    public FixDataPolicy fixDataPolicy() {
        return new FixDataPolicy();
    }
}
