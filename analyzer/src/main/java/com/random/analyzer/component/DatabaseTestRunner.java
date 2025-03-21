package com.random.analyzer.component;

import com.random.analyzer.data.DataStore;
import com.random.analyzer.model.DrawResult;
import com.random.analyzer.service.DrawResultService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseTestRunner implements CommandLineRunner {

    private final DrawResultService drawResultService;

    public DatabaseTestRunner(DrawResultService drawResultService) {
        this.drawResultService = drawResultService;
    }

    @Override
    public void run(String... args) {
        List<DrawResult> data = DataStore.DRAW_RESULTS;
        drawResultService.saveDrawResults(data);
    }
}
