package com.core.analyzer.logic;

import com.core.analyzer.dto.DrawWithBoxPattern;

import java.util.*;

public class PatternAnalyzer {
    public DrawWithBoxPattern findConsecutivePattern(List<DrawWithBoxPattern> mapped) {
        if (mapped.isEmpty()) return null;

        DrawWithBoxPattern best = mapped.get(0);
        int maxStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < mapped.size(); i++) {
            String prev = mapped.get(i - 1).getBoxPattern().toString();
            String curr = mapped.get(i).getBoxPattern().toString();

            if (curr.equals(prev)) {
                currentStreak++;
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    best = mapped.get(i); // 마지막 패턴이 있는 회차 기준
                }
            } else {
                currentStreak = 1;
            }
        }

        System.out.println("📌 가장 긴 연속 패턴 등장: " + maxStreak + "회");
        return best;
    }
}

