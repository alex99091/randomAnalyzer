package com.core.analyzer.drawResult;

public class DrawResult {
    private int idx;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;

    public DrawResult() {}

    public DrawResult(int idx, int number1, int number2, int number3, int number4, int number5, int number6) {
        this.idx = idx;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
        this.number6 = number6;
    }

    @Override
    public String toString() {
        return "DrawResult{" +
                "idx=" + idx +
                ", number1=" + number1 +
                ", number2=" + number2 +
                ", number3=" + number3 +
                ", number4=" + number4 +
                ", number5=" + number5 +
                ", number6=" + number6 +
                '}';
    }


    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getNumber4() {
        return number4;
    }

    public void setNumber4(int number4) {
        this.number4 = number4;
    }

    public int getNumber5() {
        return number5;
    }

    public void setNumber5(int number5) {
        this.number5 = number5;
    }

    public int getNumber6() {
        return number6;
    }

    public void setNumber6(int number6) {
        this.number6 = number6;
    }
}
