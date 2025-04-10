package com.core.analyzer.app;

import com.core.analyzer.config.AppConfig;
import com.core.analyzer.service.analyze.FixDataPolicy;
import com.core.analyzer.model.data.RowData;
import com.core.analyzer.model.boxResult.BoxResult;
import com.core.analyzer.domain.draw.DrawResult;
import com.core.analyzer.domain.draw.DrawResultService;
import com.core.analyzer.domain.pattern.DrawWithBoxPattern;
import com.core.analyzer.engine.generate.Generator;
import com.core.analyzer.engine.similarity.CosineSimilarity;
import com.core.analyzer.engine.similarity.L1Similarity;
import com.core.analyzer.engine.similarity.L2Similarity;
import com.core.analyzer.engine.similarity.SimilarityAnalyzer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CoreApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        DrawResultService drawResultService = appConfig.drawResultService();

        RowData rowData = new RowData();
        FixDataPolicy fixDataPolicy = new FixDataPolicy();

        // 1. 원시 데이터 파싱
        String rawData = rowData.getRowData();
        List<DrawResult> parsedResult = fixDataPolicy.readData(rawData);

        // 2. 박스 분류 결과 생성
        List<BoxResult> boxResults = fixDataPolicy.readBoxData(parsedResult);

        // ✅ 최신 2개 회차 제외
        int excludeCount = 2;
        int totalSize = parsedResult.size();
        List<DrawResult> truncatedResult = parsedResult.subList(0, totalSize - excludeCount);
        List<BoxResult> truncatedBox = boxResults.subList(0, totalSize - excludeCount);

        // 2.5. 최신 회차 정보 및 박스 패턴 출력
        fixDataPolicy.printLastDrawAndBoxPattern(parsedResult, boxResults);

        // 3. 최신 회차 기준 BoxPattern 추출
        List<Integer> lastBoxPattern = fixDataPolicy.extractLastBoxPattern(parsedResult, boxResults);
        System.out.println("✅ 최신 회차 기준 박스 패턴: " + lastBoxPattern);

        // 4. 해당 패턴과 동일한 idx 찾기 (✅ 최신 회차 제외)
        List<Integer> matchingIdxList = fixDataPolicy.findMatchingIdxByBoxPattern(
                boxResults.subList(0, boxResults.size() - 1),
                lastBoxPattern
        );
        System.out.println("📌 해당 패턴과 일치하는 회차 정보: " + matchingIdxList.size() + "개");
        printDraws(parsedResult, matchingIdxList);

        // 5. 희소한 패턴 자동 탐색 (최신 2개 회차 제외)
        Map.Entry<List<Integer>, List<Integer>> rarestResult = fixDataPolicy.findRarestBoxPattern(truncatedResult, truncatedBox);
        List<Integer> rarestPattern = rarestResult.getKey();
        List<Integer> rarestIdxList = rarestResult.getValue();

        if (!rarestPattern.isEmpty()) {
            int baseIdx = rarestIdxList.stream().mapToInt(i -> i).max().orElse(-1);
            System.out.println("패턴유사반복 재실행 " + rarestIdxList.size() + "회 → 기준 회차: " + baseIdx + ", 등장 횟수: " + rarestIdxList.size());
            System.out.println("📦 희소한 BoxPattern: " + rarestPattern);
            System.out.println("📌 해당 패턴과 일치하는 회차 정보: " + rarestIdxList.size() + "개");
            printDraws(parsedResult, rarestIdxList);
        } else {
            System.out.println("❌ 희소한 패턴을 찾지 못했습니다.");
            return;
        }

        // 6. 유사도 분석 (truncated 기준)
        List<DrawWithBoxPattern> mappedRarest = fixDataPolicy.mapToBoxPatternResult(truncatedResult, truncatedBox);

        SimilarityAnalyzer l1Analyzer = new SimilarityAnalyzer(new L1Similarity());
        SimilarityAnalyzer l2Analyzer = new SimilarityAnalyzer(new L2Similarity());
        SimilarityAnalyzer cosineAnalyzer = new SimilarityAnalyzer(new CosineSimilarity());

        int[] rarestTarget = toIntArray(rarestPattern);
        SimilarityAnalyzer.Result l1 = l1Analyzer.findMostSimilarWithScore(rarestTarget, mappedRarest);
        SimilarityAnalyzer.Result l2 = l2Analyzer.findMostSimilarWithScore(rarestTarget, mappedRarest);
        SimilarityAnalyzer.Result cosine = cosineAnalyzer.findMostSimilarWithScore(rarestTarget, mappedRarest);

        System.out.println("\n🧪 분석기 유사도 비교:");
        System.out.printf(" - L1 점수:      %.3f\n", l1.score());
        System.out.printf(" - L2 점수:      %.3f\n", l2.score());
        System.out.printf(" - Cosine 점수:  %.3f\n", cosine.score());

        String best = (l1.score() <= l2.score() && l1.score() <= cosine.score()) ? "L1"
                : (l2.score() <= cosine.score()) ? "L2"
                : "Cosine";
        System.out.println("✅ 최적 분석기 선택됨: " + best);

        // 7. 자동 생성기 결과 출력
        Generator generator = new Generator();

        Map<String, SimilarityAnalyzer.Result> resultMap = Map.of(
                "L1", l1,
                "L2", l2,
                "Cosine", cosine
        );

        System.out.println("\n🎰 분석기별 5게임 자동 생성 결과:");
        for (Map.Entry<String, SimilarityAnalyzer.Result> entry : resultMap.entrySet()) {
            String name = entry.getKey();
            SimilarityAnalyzer.Result result = entry.getValue();
            int[] patternArray = toIntArray(result.pattern().getBoxPattern());

            System.out.println("📦 " + name + " 예측된 boxPattern: " + Arrays.toString(patternArray));
            List<List<Integer>> games = generator.generateMultiple(patternArray, 5);

            for (int i = 0; i < games.size(); i++) {
                System.out.println("Game " + (i + 1) + ": " + games.get(i));
            }
            System.out.println();
        }
    }

    private static void printDraws(List<DrawResult> all, List<Integer> idxList) {
        for (Integer idx : idxList) {
            all.stream()
                    .filter(dr -> dr.getIdx() == idx)
                    .findFirst()
                    .ifPresent(dr -> System.out.println(
                            " - 회차: " + dr.getIdx() + " | 번호: " +
                                    dr.getNumber1() + ", " + dr.getNumber2() + ", " + dr.getNumber3() + ", " +
                                    dr.getNumber4() + ", " + dr.getNumber5() + ", " + dr.getNumber6()
                    ));
        }
    }

    private static int[] toIntArray(List<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
    }
}