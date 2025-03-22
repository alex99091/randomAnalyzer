package com.core.analyzer.dto;

import java.util.List;

public class DrawWithBoxPattern {
    private int idx;
    private List<Integer> numbers;
    private List<Integer> boxPattern;

    public DrawWithBoxPattern(int idx, List<Integer> numbers, List<Integer> boxPattern) {
        this.idx = idx;
        this.numbers = numbers;
        this.boxPattern = boxPattern;
    }

    @Override
    public String toString() {
        return "[" + idx + "회차] 번호: " + numbers + " → 박스 패턴: " + boxPattern;
    }

    public int getIdx() {
        return idx;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<Integer> getBoxPattern() {
        return boxPattern;
    }
}
