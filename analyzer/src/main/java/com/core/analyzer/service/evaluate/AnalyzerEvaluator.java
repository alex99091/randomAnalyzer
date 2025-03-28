package com.core.analyzer.service.evaluate;

import com.core.analyzer.model.boxResult.BoxResult;

import java.util.List;

public class AnalyzerEvaluator {

    public static double evaluate(int currentIdx, List<BoxResult> boxResults, int[] predictedPattern) {
        int nextIdx = currentIdx + 1;
        for (BoxResult b : boxResults) {
            if (b.getIdx() == nextIdx) {
                int[] actual = {
                        b.getBox1(), b.getBox2(), b.getBox3(), b.getBox4(), b.getBox5()
                };
                return calculateL1Distance(predictedPattern, actual); // 낮을수록 유사
            }
        }
        return Double.MAX_VALUE;
    }

    private static double calculateL1Distance(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        return sum;
    }
}

