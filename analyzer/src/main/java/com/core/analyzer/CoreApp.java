package com.core.analyzer;

import com.core.analyzer.analyze.FixDataPolicy;
import com.core.analyzer.analyze.RowData;
import com.core.analyzer.boxResult.BoxResult;
import com.core.analyzer.drawResult.DrawResult;
import com.core.analyzer.drawResult.DrawResultService;
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

        // 2.5. 최신 회차 정보 및 박스 패턴 출력
        fixDataPolicy.printLastDrawAndBoxPattern(parsedResult, boxResults);

        // 3. 최신 회차 기준 BoxPattern 추출
        List<Integer> lastBoxPattern = fixDataPolicy.extractLastBoxPattern(parsedResult, boxResults);
        System.out.println("✅ 최신 회차 기준 박스 패턴: " + lastBoxPattern);


        // 4. 해당 패턴과 동일한 idx 찾기R
        List<Integer> matchingIdxList = fixDataPolicy.findMatchingIdxByBoxPattern(boxResults, lastBoxPattern);

        // 출력
        System.out.println("📌 해당 패턴과 일치하는 회차 idx 목록:");
        for (Integer idx : matchingIdxList) {
            System.out.println(" - 회차: " + idx);
        }

    }

    private static int[] toIntArray(List<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
    }
}