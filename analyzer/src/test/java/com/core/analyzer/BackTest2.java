package com.core.analyzer;

import com.core.analyzer.domain.draw.DrawResult;
import com.core.analyzer.domain.pattern.DrawWithBoxPattern;
import com.core.analyzer.engine.similarity.CosineSimilarity;
import com.core.analyzer.engine.similarity.SimilarityAnalyzer;
import com.core.analyzer.model.boxResult.BoxResult;
import com.core.analyzer.model.data.RowData;
import com.core.analyzer.service.analyze.FixDataPolicy;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;


public class BackTest2 {

    @Test
    public void runRarePatternBasedBacktest() {
        // 1. 데이터 로드 및 전처리
        RowData rowData = new RowData();
        FixDataPolicy policy = new FixDataPolicy();
        List<DrawResult> allDraws = policy.readData(rowData.getRowData());
        List<BoxResult> allBoxes = policy.readBoxData(allDraws);

        // 2. 최신 회차 제외 (미래 정보 방지)
        List<DrawResult> truncatedDraws = allDraws.subList(0, allDraws.size() - 1);
        List<BoxResult> truncatedBoxes = allBoxes.subList(0, allBoxes.size() - 1);

        // 3. 최신 회차 박스 패턴 기준 유사 예측
        DrawResult latest = allDraws.get(allDraws.size() - 1);
        List<Integer> latestPattern = policy.extractLastBoxPattern(List.of(latest), allBoxes);

        System.out.println("\u2705 최신 회차 번호: " + latest.getNumbers());
        System.out.println("\uD83D\uDCC6 최신 박스 패턴: " + latestPattern);

        List<Integer> matchingIdx = policy.findMatchingIdxByBoxPattern(truncatedBoxes, latestPattern);
        System.out.println("\uD83D\uDD0D 과거 동일 패턴 회차 수: " + matchingIdx.size());

        if (matchingIdx.isEmpty()) {
            System.out.println("\u274C 동일한 패턴 회차 없음");
            return;
        }

        // 4. 연속 패턴 존재 여부 판단
        Set<Integer> baseIdxSet = findSequentialOrAll(matchingIdx);

        // 5. 벡터화된 전체 패턴 리스트 준비
        List<DrawWithBoxPattern> mapped = policy.mapToBoxPatternResult(allDraws, allBoxes);
        SimilarityAnalyzer cosineAnalyzer = new SimilarityAnalyzer(new CosineSimilarity());

        // 6. 백테스트 실행
        int success = 0;
        int total = 0;

        for (Integer baseIdx : baseIdxSet) {
            DrawResult baseDraw = allDraws.stream().filter(d -> d.getIdx() == baseIdx).findFirst().orElse(null);
            BoxResult baseBox = allBoxes.stream().filter(b -> b.getIdx() == baseIdx).findFirst().orElse(null);
            BoxResult nextBox = allBoxes.stream().filter(b -> b.getIdx() == baseIdx + 1).findFirst().orElse(null);

            if (baseDraw == null || baseBox == null || nextBox == null) continue;

            // Step 1: 박스 패턴 예측력 확인
            boolean boxPatternMatches = isPatternMatch(nextBox, baseBox.getPatternList());
            if (!boxPatternMatches) continue; // 박스 반복성이 없으면 스킵

            DrawResult actual = allDraws.stream()
                    .filter(d -> d.getIdx() == baseIdx + 1)
                    .findFirst().orElse(null);
            if (actual == null) continue;

            DrawWithBoxPattern basePattern = mapped.stream()
                    .filter(p -> p.getIdx() == baseIdx)
                    .findFirst().orElse(null);
            if (basePattern == null) continue;

            // 🎯 벡터로 변환 (boxPattern + mean + std)
            int[] targetPattern = basePattern.getBoxPattern().stream().mapToInt(i -> i).toArray();

            // 유사도 분석
            SimilarityAnalyzer.Result result = cosineAnalyzer.findMostSimilarWithScore(targetPattern, mapped);

            DrawWithBoxPattern matchedPattern = result.pattern();
            DrawResult predicted = allDraws.stream()
                    .filter(d -> d.getIdx() == matchedPattern.getIdx())
                    .findFirst().orElse(null);
            if (predicted == null) continue;

            int match = countMatchedNumbers(predicted, actual);
            if (match >= 3) success++;
            total++;

            System.out.printf("\uD83D\uDD39 유사도 예측 회차: %d → 실제: %d | 예측 번호: %s | 실제 번호: %s | 일치: %d개\n",
                    predicted.getIdx(), actual.getIdx(), predicted.getNumbers(), actual.getNumbers(), match);
        }


        // 7. 요약 출력
        System.out.println("\n\u2705 백테스트 요약 (유사도 기반)");
        System.out.println("총 테스트 대상: " + total);
        System.out.println("3개 이상 일치 성공: " + success);
        System.out.printf("정확도: %.2f%%\n", total == 0 ? 0 : (100.0 * success / total));
    }

    private int countMatchedNumbers(DrawResult a, DrawResult b) {
        Set<Integer> setA = a.getNumbersAsSet();
        Set<Integer> setB = b.getNumbersAsSet();
        setA.retainAll(setB);
        return setA.size();
    }

    private Set<Integer> findSequentialOrAll(List<Integer> sortedList) {
        List<Integer> sorted = sortedList.stream().sorted().collect(Collectors.toList());
        Set<Integer> baseSet = new LinkedHashSet<>();

        for (int i = 0; i < sorted.size() - 1; i++) {
            int cur = sorted.get(i);
            int next = sorted.get(i + 1);
            if (next - cur == 1) baseSet.add(next);
        }

        if (baseSet.isEmpty()) {
            System.out.println("⚠️ 연속 패턴 없음 → 단일 회차 전체 사용");
            baseSet.addAll(sorted);
        }

        return baseSet;
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
}