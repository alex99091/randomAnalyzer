package com.core.analyzer.util;

import com.core.analyzer.domain.pattern.DrawWithBoxPattern;

import java.util.ArrayList;
import java.util.List;

public class VectorUtils {
    public static List<Double> mergeVector(DrawWithBoxPattern p) {
        List<Double> vector = new ArrayList<>();
        for (Integer i : p.getBoxPattern()) vector.add(i.doubleValue());
        for (Double d : p.getBoxMeans()) vector.add(d);
        for (Double d : p.getBoxStds()) vector.add(d);
        return vector;
    }
}
