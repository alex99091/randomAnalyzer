package com.core.analyzer.model.boxResult;

import java.util.Arrays;
import java.util.List;

public class BoxResult {
    /*  BoxResult 분류기법
        box 이름	오비탈	숫자 범위
        box1	1s	1–2
        box2	2s, 2p	3–10
        box3	3s, 3p	11–18
        box4	3d, 4s, 4p	19–30
        box5	5p	31–32
        box6	4p	33–36
        box7	5s, 4d	37–45
    * */

    private int idx;
    private int box1;
    private int box2;
    private int box3;
    private int box4;
    private int box5;
    private int box6;
    private int box7;
    private double[] boxMeans = new double[8];  // box[1] ~ box[7]
    private double[] boxStds = new double[8];

    public BoxResult(int idx, int box1, int box2, int box3, int box4, int box5, int box6, int box7) {
        this.idx = idx;
        this.box1 = box1;
        this.box2 = box2;
        this.box3 = box3;
        this.box4 = box4;
        this.box5 = box5;
        this.box6 = box6;
        this.box7 = box7;
    }

    @Override
    public String toString() {
        return "BoxResult{" +
                "idx=" + idx +
                ", box1=" + box1 +
                ", box2=" + box2 +
                ", box3=" + box3 +
                ", box4=" + box4 +
                ", box5=" + box5 +
                ", box6=" + box6 +
                ", box7=" + box7 +
                '}';
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getBox1() {
        return box1;
    }

    public void setBox1(int box1) {
        this.box1 = box1;
    }

    public int getBox2() {
        return box2;
    }

    public void setBox2(int box2) {
        this.box2 = box2;
    }

    public int getBox3() {
        return box3;
    }

    public void setBox3(int box3) {
        this.box3 = box3;
    }

    public int getBox4() {
        return box4;
    }

    public void setBox4(int box4) {
        this.box4 = box4;
    }

    public int getBox5() {
        return box5;
    }

    public void setBox5(int box5) {
        this.box5 = box5;
    }

    public int getBox6() {
        return box6;
    }

    public void setBox6(int box6) {
        this.box6 = box6;
    }

    public int getBox7() {
        return box7;
    }

    public void setBox7(int box7) {
        this.box7 = box7;
    }

    public double[] getBoxMeans() {
        return boxMeans;
    }

    public void setBoxMeans(double[] boxMeans) {
        this.boxMeans = boxMeans;
    }

    public double[] getBoxStds() {
        return boxStds;
    }

    public void setBoxStds(double[] boxStds) {
        this.boxStds = boxStds;
    }

    public List<Integer> getPatternList() {
        return Arrays.asList(
                getBox1(), getBox2(), getBox3(),
                getBox4(), getBox5(), getBox6(), getBox7()
        );
    }

    public int getBoxByIndex(int i) {
        return switch (i) {
            case 1 -> box1;
            case 2 -> box2;
            case 3 -> box3;
            case 4 -> box4;
            case 5 -> box5;
            case 6 -> box6;
            case 7 -> box7;
            default -> throw new IllegalArgumentException("Invalid box index: " + i);
        };
    }


}
