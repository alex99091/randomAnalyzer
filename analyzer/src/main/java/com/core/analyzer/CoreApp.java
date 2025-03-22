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
import com.core.analyzer.similarity.CosineSimilarity;
import com.core.analyzer.similarity.L1Similarity;
import com.core.analyzer.similarity.L2Similarity;
import com.core.analyzer.similarity.SimilarityAnalyzer;

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

        // 분석 대상 패턴 (예: 현재 check와 비슷한 패턴 찾기)
        int[] targetPattern = check;

        // L1 분석기
        SimilarityAnalyzer l1Analyzer = new SimilarityAnalyzer(new L1Similarity());
        DrawWithBoxPattern l1Match = l1Analyzer.findMostSimilar(targetPattern, mapped);
        System.out.println("📏 L1 유사도 결과: " + l1Match);

        // L2 분석기
        SimilarityAnalyzer l2Analyzer = new SimilarityAnalyzer(new L2Similarity());
        DrawWithBoxPattern l2Match = l2Analyzer.findMostSimilar(targetPattern, mapped);
        System.out.println("📐 L2 유사도 결과: " + l2Match);

        // Cosine 분석기
        SimilarityAnalyzer cosineAnalyzer = new SimilarityAnalyzer(new CosineSimilarity());
        DrawWithBoxPattern cosineMatch = cosineAnalyzer.findMostSimilar(targetPattern, mapped);
        System.out.println("🧭 Cosine 유사도 결과: " + cosineMatch);

        // 8. 분석결과를 바탕으로 번호생성기
        Generator generator = new Generator();

        // 평가: 다음 회차 박스패턴과 얼마나 유사한가 (작을수록 좋음)
        double l1Score = AnalyzerEvaluator.evaluate(
                l1Match.getIdx(),
                boxResults,
                l1Match.getBoxPattern().stream().mapToInt(i -> i).toArray()
        );

        double l2Score = AnalyzerEvaluator.evaluate(
                l2Match.getIdx(),
                boxResults,
                l2Match.getBoxPattern().stream().mapToInt(i -> i).toArray()
        );

        double cosineScore = AnalyzerEvaluator.evaluate(
                cosineMatch.getIdx(),
                boxResults,
                cosineMatch.getBoxPattern().stream().mapToInt(i -> i).toArray()
        );

        if (l1Score <= l2Score && l1Score <= cosineScore) {
            System.out.println("✅ L1 분석기 선택됨 (예측 점수: " + l1Score + ")");
            targetPattern = l1Match.getBoxPattern().stream().mapToInt(i -> i).toArray();
        } else if (l2Score <= cosineScore) {
            System.out.println("✅ L2 분석기 선택됨 (예측 점수: " + l2Score + ")");
            targetPattern = l2Match.getBoxPattern().stream().mapToInt(i -> i).toArray();
        } else {
            System.out.println("✅ Cosine 분석기 선택됨 (예측 점수: " + cosineScore + ")");
            targetPattern = cosineMatch.getBoxPattern().stream().mapToInt(i -> i).toArray();
        }

        // 번호 생성
        List<List<Integer>> games = generator.generateMultiple(targetPattern, 5);

        System.out.println("🎰 5게임 자동 생성 결과:");
        for (int i = 0; i < games.size(); i++) {
            System.out.println("Game " + (i + 1) + ": " + games.get(i));
        }

    }
}