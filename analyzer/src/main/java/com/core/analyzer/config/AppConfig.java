package com.core.analyzer.config;

import com.core.analyzer.service.analyze.FixDataPolicy;
import com.core.analyzer.domain.draw.DrawResultService;
import com.core.analyzer.domain.draw.DrawResultServiceImpl;
import com.core.analyzer.domain.draw.MemoryDrawResultRepository;

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
