package com.core.analyzer.analyze;

import com.core.analyzer.drawResult.DrawResult;

import java.util.List;

public interface HandleData {
    List<DrawResult> readData(String rowData);
}
