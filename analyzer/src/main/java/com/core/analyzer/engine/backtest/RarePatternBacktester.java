package com.core.analyzer.engine.backtest;

import com.core.analyzer.domain.draw.DrawResult;
import com.core.analyzer.domain.pattern.DrawWithBoxPattern;
import com.core.analyzer.model.boxResult.BoxResult;
import com.core.analyzer.service.analyze.FixDataPolicy;

import java.util.*;

public class RarePatternBacktester {

    public void runBacktest(List<DrawResult> allDraws, List<BoxResult> allBoxes, FixDataPolicy policy) {
        int successCount = 0;
        int totalTests = 0;
        int match3 = 0, match4 = 0, match5 = 0, match6 = 0;

        for (int i = 10; i < allDraws.size() - 1; i++) {
            List<DrawResult> pastDraws = allDraws.subList(0, i);
            List<BoxResult> pastBoxes = allBoxes.subList(0, i);

            Map.Entry<List<Integer>, List<Integer>> result = policy.findRarestBoxPattern(pastDraws, pastBoxes);
            List<Integer> pattern = result.getKey();
            List<Integer> idxList = result.getValue();

            if (pattern.isEmpty() || idxList.isEmpty()) continue;

            DrawResult next = allDraws.get(i);
            BoxResult nextBox = allBoxes.get(i);

            if (isPatternMatch(nextBox, pattern)) {
                successCount++;
            }

            int latestRarestIdx = idxList.stream().mapToInt(v -> v).max().orElse(-1);
            DrawResult predicted = allDraws.stream()
                    .filter(d -> d.getIdx() == latestRarestIdx)
                    .findFirst().orElse(null);

            if (predicted == null) continue;

            int matchCount = countMatchedNumbers(predicted, next);
            if (matchCount == 3) match3++;
            else if (matchCount == 4) match4++;
            else if (matchCount == 5) match5++;
            else if (matchCount == 6) match6++;

            System.out.printf("\uD83D\uDCCC [회차 %d → %d] 예측 번호: %s | 실제 번호: %s | 일치: %d개\n",
                    predicted.getIdx(), next.getIdx(), predicted.getNumbers(), next.getNumbers(), matchCount);

            totalTests++;
        }

        System.out.println("\n✅ 백테스트 완료");
        System.out.println("총 테스트 횟수: " + totalTests + "회");
        System.out.println("성공 횟수 (패턴 일치): " + successCount + "회");
        System.out.printf("정확도: %.2f%%\n", (100.0 * successCount / totalTests));
        System.out.printf("번호 3개 일치: %d회\n", match3);
        System.out.printf("번호 4개 일치: %d회\n", match4);
        System.out.printf("번호 5개 일치: %d회\n", match5);
        System.out.printf("번호 6개 일치 (Jackpot): %d회 \uD83C\uDF89\n", match6);
    }

    private boolean isPatternMatch(BoxResult box, List<Integer> pattern) {
        if (pattern == null || pattern.size() != 7) return false;
        return box.getBox1() == pattern.get(0) &&
                box.getBox2() == pattern.get(1) &&
                box.getBox3() == pattern.get(2) &&
                box.getBox4() == pattern.get(3) &&
                box.getBox5() == pattern.get(4) &&
                box.getBox6() == pattern.get(5) &&
                box.getBox7() == pattern.get(6);
    }

    private int countMatchedNumbers(DrawResult a, DrawResult b) {
        Set<Integer> aSet = a.getNumbersAsSet();
        Set<Integer> bSet = b.getNumbersAsSet();
        aSet.retainAll(bSet);
        return aSet.size();
    }

    private static List<Double> mergeVector(DrawWithBoxPattern p) {
        List<Double> vector = new ArrayList<>();
        for (Integer i : p.getBoxPattern()) vector.add(i.doubleValue());
        for (Double d : p.getBoxMeans()) vector.add(d);
        for (Double d : p.getBoxStds()) vector.add(d);
        return vector;
    }

}


