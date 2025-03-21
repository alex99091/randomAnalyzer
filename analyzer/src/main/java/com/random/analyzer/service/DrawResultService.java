package com.random.analyzer.service;

import com.random.analyzer.model.DrawResult;
import com.random.analyzer.repository.DrawResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DrawResultService {

    private final DrawResultRepository drawResultRepository;

    public DrawResultService(DrawResultRepository drawResultRepository) {
        this.drawResultRepository = drawResultRepository;
    }

    // ✅ H2 DB에 데이터 저장
    @Transactional
    public void saveDrawResults(List<DrawResult> drawResults) {
        drawResultRepository.saveEach(drawResults);  // 리스트 전체 저장
        System.out.println("✅ DB에 " + drawResults.size() + "개의 데이터 저장 완료!");
    }

}
