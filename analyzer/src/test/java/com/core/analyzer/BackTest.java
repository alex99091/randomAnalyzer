package com.core.analyzer;

import com.core.analyzer.domain.draw.DrawResult;
import com.core.analyzer.engine.backtest.RarePatternBacktester;
import com.core.analyzer.model.boxResult.BoxResult;
import com.core.analyzer.model.data.RowData;
import com.core.analyzer.service.analyze.FixDataPolicy;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class BackTest {

    @Test
    public void runPatternBasedBacktest() {
        RowData rowData = new RowData();
        FixDataPolicy policy = new FixDataPolicy();
        List<DrawResult> allDraws = policy.readData(rowData.getRowData());
        List<BoxResult> allBoxes = policy.readBoxData(allDraws);

        List<DrawResult> truncatedDraws = allDraws.subList(0, allDraws.size() - 1);
        List<BoxResult> truncatedBoxes = allBoxes.subList(0, allBoxes.size() - 1);

        DrawResult last = allDraws.get(allDraws.size() - 1);
        int lastIdx = last.getIdx();
        List<Integer> lastPattern = policy.extractLastBoxPattern(List.of(last), allBoxes);

        System.out.println("✅ 최신 회차 번호: " + last.getNumbers());
        System.out.println("📦 최신 박스 패턴: " + lastPattern);

        List<Integer> matchingIdx = policy.findMatchingIdxByBoxPattern(truncatedBoxes, lastPattern);
        System.out.println("🔍 과거 동일 패턴 회차 수: " + matchingIdx.size());

        if (matchingIdx.isEmpty()) {
            System.out.println("❌ 동일한 패턴 회차 없음");
            return;
        }

        List<Integer> sorted = matchingIdx.stream().sorted().collect(Collectors.toList());
        Set<Integer> baseIdxSet = new LinkedHashSet<>();

        for (int i = 0; i < sorted.size() - 1; i++) {
            int current = sorted.get(i);
            int next = sorted.get(i + 1);
            if (next - current == 1) {
                baseIdxSet.add(next);
            }
        }

        if (baseIdxSet.isEmpty()) {
            System.out.println("⚠️ 연속 패턴 없음 → 단일 회차 전체 사용");
            baseIdxSet.addAll(sorted);
        }

        int success = 0;
        int total = 0;

        for (Integer baseIdx : baseIdxSet) {
            DrawResult predicted = allDraws.stream()
                    .filter(d -> d.getIdx() == baseIdx)
                    .findFirst().orElse(null);

            DrawResult actual = allDraws.stream()
                    .filter(d -> d.getIdx() == baseIdx + 1)
                    .findFirst().orElse(null);

            if (predicted == null || actual == null) continue;

            int match = countMatchedNumbers(predicted, actual);
            if (match >= 3) success++;
            total++;

            System.out.printf("\uD83D\uDD39 예측 회차: %d → 실제: %d | 예측 번호: %s | 실제 번호: %s | 일치: %d개\n",
                    predicted.getIdx(), actual.getIdx(),
                    predicted.getNumbers(), actual.getNumbers(), match);
        }

        System.out.println("\n✅ 백테스트 요약");
        System.out.println("총 테스트 대상: " + total);
        System.out.println("3개 이상 일치 성공: " + success);
        System.out.printf("정확도: %.2f%%\n", total == 0 ? 0 : (100.0 * success / total));
    }

    private int countMatchedNumbers(DrawResult a, DrawResult b) {
        Set<Integer> aSet = a.getNumbersAsSet();
        Set<Integer> bSet = b.getNumbersAsSet();
        aSet.retainAll(bSet);
        return aSet.size();
    }
}
