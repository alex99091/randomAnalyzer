package com.core.analyzer;

import com.core.analyzer.analyze.FixDataPolicy;
import com.core.analyzer.drawResult.DrawResult;
import com.core.analyzer.drawResult.DrawResultService;
import com.core.analyzer.drawResult.DrawResultServiceImpl;

import java.util.List;

public class CoreApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        DrawResultService drawResultService = appConfig.drawResultService();

        FixDataPolicy fixDataPolicy = new FixDataPolicy();
        String rawData = FixDataPolicy.getRowData();  // static 메서드로 접근
        List<DrawResult> parsedResult = fixDataPolicy.readData(rawData);

        System.out.println(parsedResult);

    }
}
