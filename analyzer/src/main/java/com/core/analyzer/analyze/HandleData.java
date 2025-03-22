package com.core.analyzer.analyze;

import com.core.analyzer.drawResult.DrawResult;

public interface HandleData {
    DrawResult readData(String rowData, DrawResult drawResult);
}
