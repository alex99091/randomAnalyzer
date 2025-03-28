package com.core.analyzer;

import com.core.analyzer.analyze.FixDataPolicy;
import com.core.analyzer.analyze.RowData;
import com.core.analyzer.boxResult.BoxResult;
import com.core.analyzer.drawResult.DrawResult;
import com.core.analyzer.dto.DrawWithBoxPattern;
import com.core.analyzer.evaluate.AnalyzerEvaluator;
import com.core.analyzer.generator.Generator;
import com.core.analyzer.similarity.*;

import java.util.*;

public class ModifiedApp {
    public static void main(String[] args) {
        // === 초기화 ===
        FixDataPolicy policy = new FixDataPolicy();
        List<DrawResult> parsedResult = policy.readData(new RowData().getRowData());
        List<BoxResult> boxResults = policy.readBoxData(parsedResult);

        // === 테스트 패턴 ===
        int[] check = {0, 0, 4, 2, 0};
        //int[] check = {1, 3, 0, 1, 1};
        //int[] check = {0, 2, 2, 0, 2};

        // === 필터링 및 매핑 ===
        List<DrawResult> filtered = policy.filterBoxCheck(parsedResult, boxResults, check);
        List<DrawResult> fixed = policy.fixedBoxData(parsedResult, boxResults, check);
        List<DrawWithBoxPattern> mapped = policy.mapToBoxPatternResult(fixed, boxResults);

        printSection("필터링된 DrawResult", filtered);
        printSection("박스 Fixed 패턴 매핑 결과", mapped);

        // === 유사도 분석 ===
        Map<String, DrawWithBoxPattern> similarityMap = analyzeSimilarity(check, mapped);
        Map<String, Double> scoreMap = evaluateSimilarity(similarityMap, boxResults);

        // === 연속 패턴 분석 ===
        DrawWithBoxPattern streakMatch = findConsecutivePattern(mapped);

        int[] targetPattern;
        if (streakMatch != null) {
            int streakCount = countConsecutive(mapped, streakMatch.getBoxPattern());
            System.out.println("\uD83D\uDCCC 가장 긴 연속 패턴 등장: " + streakCount + "회");
            if (streakCount >= 2) {
                System.out.println("✅ 연속 등장 패턴 우선 적용 (스트릭 " + streakCount + "회)");
                targetPattern = toIntArray(streakMatch.getBoxPattern());
            } else {
                targetPattern = selectBestPattern(scoreMap, similarityMap);
            }
        } else {
            targetPattern = selectBestPattern(scoreMap, similarityMap);
        }

        // === 번호 생성 ===
        Generator generator = new Generator();
        List<List<Integer>> games = generator.generateMultiple(targetPattern, 5);
        printGames("\uD83C\uDFB0 5게임 자동 생성 결과", games);
    }

    private static void printSection(String title, List<?> list) {
        System.out.println("==== " + title + " ====");
        list.forEach(System.out::println);
    }

    private static void printGames(String title, List<List<Integer>> games) {
        System.out.println(title);
        for (int i = 0; i < games.size(); i++) {
            System.out.println("Game " + (i + 1) + ": " + games.get(i));
        }
    }

    private static Map<String, DrawWithBoxPattern> analyzeSimilarity(int[] check, List<DrawWithBoxPattern> mapped) {
        Map<String, DrawWithBoxPattern> result = new HashMap<>();
        result.put("L1", new SimilarityAnalyzer(new L1Similarity()).findMostSimilar(check, mapped));
        result.put("L2", new SimilarityAnalyzer(new L2Similarity()).findMostSimilar(check, mapped));
        result.put("Cosine", new SimilarityAnalyzer(new CosineSimilarity()).findMostSimilar(check, mapped));
        result.forEach((k, v) -> System.out.println("" + k + " 유사도 결과: " + v));
        return result;
    }

    private static Map<String, Double> evaluateSimilarity(Map<String, DrawWithBoxPattern> map, List<BoxResult> boxResults) {
        Map<String, Double> scores = new HashMap<>();
        for (String key : map.keySet()) {
            double score = AnalyzerEvaluator.evaluate(
                    map.get(key).getIdx(),
                    boxResults,
                    toIntArray(map.get(key).getBoxPattern())
            );
            scores.put(key, score);
        }
        return scores;
    }

    private static DrawWithBoxPattern findConsecutivePattern(List<DrawWithBoxPattern> mapped) {
        mapped.sort(Comparator.comparingInt(DrawWithBoxPattern::getIdx));
        return mapped.isEmpty() ? null : mapped.get(mapped.size() - 1);
    }

    private static int countConsecutive(List<DrawWithBoxPattern> mapped, List<Integer> targetPattern) {
        int count = 1;
        for (int i = mapped.size() - 2; i >= 0; i--) {
            if (mapped.get(i).getBoxPattern().equals(targetPattern)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private static int[] selectBestPattern(Map<String, Double> scores, Map<String, DrawWithBoxPattern> patterns) {
        return scores.entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(entry -> toIntArray(patterns.get(entry.getKey()).getBoxPattern()))
                .orElseThrow();
    }

    private static int[] toIntArray(List<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
    }
}
