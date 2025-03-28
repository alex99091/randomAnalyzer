package com.core.analyzer.domain.draw;

public class DrawResultServiceImpl implements DrawResultService {

    private final DrawResultRepository drawResultRepository;

    public DrawResultServiceImpl(MemoryDrawResultRepository memoryDrawResultRepository) {
        this.drawResultRepository = memoryDrawResultRepository;
    }

    @Override
    public void save(DrawResult drawResult) {
        drawResultRepository.save(drawResult);
    }

    @Override
    public DrawResult findDrawResult(int idx) {
        return drawResultRepository.findById(idx);
    }
}
