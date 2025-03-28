package com.core.analyzer.domain.pattern;

import java.util.List;

public class DrawWithBoxPattern {
    private int idx;
    private List<Integer> numbers;
    private List<Integer> boxPattern;

    private double[] boxMeans;
    private double[] boxStds;

    public DrawWithBoxPattern(int idx, List<Integer> numbers, List<Integer> boxPattern, double[] boxMeans, double[] boxStds) {
        this.idx = idx;
        this.numbers = numbers;
        this.boxPattern = boxPattern;
        this.boxMeans = boxMeans;
        this.boxStds = boxStds;
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

    public double[] getBoxMeans() {
        return boxMeans;
    }

    public double[] getBoxStds() {
        return boxStds;
    }

}
