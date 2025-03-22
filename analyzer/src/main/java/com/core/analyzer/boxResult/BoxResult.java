package com.core.analyzer.boxResult;

public class BoxResult {
    private int idx;
    private int box1;
    private int box2;
    private int box3;
    private int box4;
    private int box5;

    public BoxResult() {}

    public BoxResult(int idx, int box1, int box2, int box3, int box4, int box5) {
        this.idx = idx;
        this.box1 = box1;
        this.box2 = box2;
        this.box3 = box3;
        this.box4 = box4;
        this.box5 = box5;
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
}
