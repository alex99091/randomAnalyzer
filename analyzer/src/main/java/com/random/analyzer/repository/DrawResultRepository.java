package com.random.analyzer.repository;

import com.random.analyzer.model.DrawResult;

import java.util.List;

public interface DrawResultRepository {

    DrawResult saveEach(List<DrawResult> drawResults);
    DrawResult saveRow(List<DrawResult> drawResults);
    //DrawResult findByRangeCounts(int drawIdx);

}
