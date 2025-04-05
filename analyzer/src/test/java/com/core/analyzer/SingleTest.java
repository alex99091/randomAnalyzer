package com.core.analyzer;

import com.core.analyzer.domain.draw.DrawResult;
import com.core.analyzer.engine.generate.Generator;
import com.core.analyzer.model.boxResult.BoxResult;
import com.core.analyzer.service.analyze.FixDataPolicy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SingleTest {

    private Generator generator;
    private FixDataPolicy fixDataPolicy;

    private final Function<Integer, Integer> classifyByOrbitalGroup = number -> {
        if (number <= 2) return 1;
        else if (number <= 10) return 2;
        else if (number <= 18) return 3;
        else if (number <= 30) return 4;
        else if (number <= 32) return 5;
        else if (number <= 36) return 6;
        else if (number <= 45) return 7;
        else return -1;
    };

    @Test
    public void test() {
        generator = new Generator();
        fixDataPolicy = new FixDataPolicy();
        List<DrawResult> drawResults = new ArrayList<>();

        drawResults.add(new DrawResult(1166, 7, 17, 18, 28, 30, 45));

        List<BoxResult> resultList = new ArrayList<>();

        resultList = fixDataPolicy.readBoxData(drawResults);
        System.out.println(resultList);

        List<Integer> print = new ArrayList<>();

        print = generator.generateSingle(new int[]{0, 1, 2, 2, 0, 0, 1});

        System.out.println(print);

    }
}
