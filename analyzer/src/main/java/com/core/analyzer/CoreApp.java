package com.core.analyzer;

import com.core.analyzer.analyze.FixDataPolicy;
import com.core.analyzer.analyze.RowData;
import com.core.analyzer.boxResult.BoxResult;
import com.core.analyzer.drawResult.DrawResult;
import com.core.analyzer.drawResult.DrawResultService;
import com.core.analyzer.drawResult.DrawResultServiceImpl;
import com.core.analyzer.dto.DrawWithBoxPattern;
import com.core.analyzer.evaluate.AnalyzerEvaluator;
import com.core.analyzer.generator.Generator;
import com.core.analyzer.logic.PatternAnalyzer;
import com.core.analyzer.similarity.CosineSimilarity;
import com.core.analyzer.similarity.L1Similarity;
import com.core.analyzer.similarity.L2Similarity;
import com.core.analyzer.similarity.SimilarityAnalyzer;

import java.util.Comparator;
import java.util.List;

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

        // 3. 테스트 케이스 입력 (예: box1=2, box2=4, box3~5=0)
        int[] check = {1, 3, 0, 1, 1};

        // 4. 조건에 맞는 DrawResult 필터링 [기존회차]
        List<DrawResult> filtered = fixDataPolicy.filterBoxCheck(parsedResult, boxResults, check);

        // 5. 신규 함수 적용 [이전 회차]

        List<DrawResult> fixed = fixDataPolicy.fixedBoxData(parsedResult, boxResults, check);

        // 6. 결과 출력
        System.out.println("==== 필터링된 DrawResult ====");
        for (DrawResult dr : fixed) {
            System.out.println(dr);
        }

        // 박스 패턴 DTO로 변환
        List<DrawWithBoxPattern> mapped = fixDataPolicy.mapToBoxPatternResult(fixed, boxResults);

        // 출력
        System.out.println("==== 박스 Fixed 패턴 매핑 결과 ====");
        for (DrawWithBoxPattern d : mapped) {
            System.out.println(d);
        }

        // 7. 유사도 분석 실행
        System.out.println("==== 🎯 가장 유사한 박스 패턴 찾기 ====");
        int[] checkPattern = check;

        SimilarityAnalyzer l1Analyzer = new SimilarityAnalyzer(new L1Similarity());
        SimilarityAnalyzer l2Analyzer = new SimilarityAnalyzer(new L2Similarity());
        SimilarityAnalyzer cosineAnalyzer = new SimilarityAnalyzer(new CosineSimilarity());

        DrawWithBoxPattern l1Match = l1Analyzer.findMostSimilar(checkPattern, mapped);
        DrawWithBoxPattern l2Match = l2Analyzer.findMostSimilar(checkPattern, mapped);
        DrawWithBoxPattern cosineMatch = cosineAnalyzer.findMostSimilar(checkPattern, mapped);

        System.out.println("📏 L1 유사도 결과: " + l1Match);
        System.out.println("📐 L2 유사도 결과: " + l2Match);
        System.out.println("🧭 Cosine 유사도 결과: " + cosineMatch);

        // 8. 평가 점수 계산
        double l1Score = AnalyzerEvaluator.evaluate(l1Match.getIdx(), boxResults, toIntArray(l1Match.getBoxPattern()));
        double l2Score = AnalyzerEvaluator.evaluate(l2Match.getIdx(), boxResults, toIntArray(l2Match.getBoxPattern()));
        double cosineScore = AnalyzerEvaluator.evaluate(cosineMatch.getIdx(), boxResults, toIntArray(cosineMatch.getBoxPattern()));

        // 9. 연속 등장 패턴 확인 및 가중치 반영
        // 먼저 idx 오름차순 정렬
        mapped.sort(Comparator.comparingInt(DrawWithBoxPattern::getIdx));

        // 맨 끝에서부터 연속되는 패턴 계산
        int streakCount = 1;
        DrawWithBoxPattern streakMatch = mapped.get(mapped.size() - 1);
        List<Integer> lastPattern = streakMatch.getBoxPattern();

        for (int i = mapped.size() - 2; i >= 0; i--) {
            if (mapped.get(i).getBoxPattern().equals(lastPattern)) {
                streakCount++;
            } else {
                break;
            }
        }

        System.out.println("📌 가장 긴 연속 패턴 등장: " + streakCount + "회");
        System.out.println("🔥 연속 등장 패턴 기반 분석 대상: " + streakMatch);

        int[] targetPattern;
        Generator generator = new Generator();

        if (streakCount >= 2) {
            System.out.println("✅ 연속 등장 패턴 우선 적용 (스트릭 " + streakCount + "회)");
            targetPattern = toIntArray(streakMatch.getBoxPattern());
        } else {
            // 기존 분석기 결과 선택
            if (l1Score <= l2Score && l1Score <= cosineScore) {
                System.out.println("✅ L1 분석기 선택됨 (예측 점수: " + l1Score + ")");
                targetPattern = toIntArray(l1Match.getBoxPattern());
            } else if (l2Score <= cosineScore) {
                System.out.println("✅ L2 분석기 선택됨 (예측 점수: " + l2Score + ")");
                targetPattern = toIntArray(l2Match.getBoxPattern());
            } else {
                System.out.println("✅ Cosine 분석기 선택됨 (예측 점수: " + cosineScore + ")");
                targetPattern = toIntArray(cosineMatch.getBoxPattern());
            }
        }

        // 10. 최종 번호 생성
        List<List<Integer>> games = generator.generateMultiple(targetPattern, 5);

        System.out.println("🎰 5게임 자동 생성 결과:");
        for (int i = 0; i < games.size(); i++) {
            System.out.println("Game " + (i + 1) + ": " + games.get(i));
        }
    }

    private static int[] toIntArray(List<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
    }
}