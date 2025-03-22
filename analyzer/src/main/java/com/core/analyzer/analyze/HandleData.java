package com.core.analyzer.analyze;

import com.core.analyzer.boxResult.BoxResult;
import com.core.analyzer.drawResult.DrawResult;
import com.core.analyzer.dto.DrawWithBoxPattern;

import java.util.List;

public interface HandleData {
    List<DrawResult> readData(String rowData);
    List<BoxResult> readBoxData(List<DrawResult> drawResults);
    List<DrawResult> filterBoxCheck(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check);
    List<DrawResult> fixedBoxData(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check);
    List<DrawWithBoxPattern> mapToBoxPatternResult(List<DrawResult> drawResults, List<BoxResult> boxResults);

}
