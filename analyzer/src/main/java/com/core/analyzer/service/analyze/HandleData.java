package com.core.analyzer.service.analyze;

import com.core.analyzer.model.boxResult.BoxResult;
import com.core.analyzer.domain.draw.DrawResult;
import com.core.analyzer.domain.pattern.DrawWithBoxPattern;

import java.util.List;
import java.util.Map;

public interface HandleData {
    List<DrawResult> readData(String rowData);
    List<BoxResult> readBoxData(List<DrawResult> drawResults);
    List<DrawResult> filterBoxCheck(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check);
    List<DrawResult> fixedBoxData(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check);
    List<DrawWithBoxPattern> mapToBoxPatternResult(List<DrawResult> drawResults, List<BoxResult> boxResults);
    List<Integer> extractLastBoxPattern(List<DrawResult> drawResults, List<BoxResult> boxResults);
    List<Integer> findMatchingIdxByBoxPattern(List<BoxResult> boxResults, List<Integer> boxPattern);
    void printLastDrawAndBoxPattern(List<DrawResult> drawResults, List<BoxResult> boxResults);
    Map.Entry<List<Integer>, List<Integer>> findRarestBoxPattern(List<DrawResult> drawResults, List<BoxResult> boxResults);
}
