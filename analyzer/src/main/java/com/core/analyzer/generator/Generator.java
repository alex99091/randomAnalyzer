package com.core.analyzer.generator;

import java.util.*;

public class Generator {

    private static final Map<Integer, int[]> BOX_RANGES = Map.of(
            0, new int[]{1, 10},
            1, new int[]{11, 20},
            2, new int[]{21, 30},
            3, new int[]{31, 40},
            4, new int[]{41, 45}
    );

    // 1게임 생성 (중복 없이)
    public List<Integer> generateSingle(int[] boxPattern) {
        Set<Integer> result = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < boxPattern.length; i++) {
            int count = boxPattern[i];
            int[] range = BOX_RANGES.get(i);
            int start = range[0];
            int end = range[1];

            while (count > 0) {
                int num = random.nextInt(end - start + 1) + start;
                if (result.add(num)) count--;
            }
        }

        List<Integer> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        return sorted;
    }

    // 5게임 자동 생성
    public List<List<Integer>> generateMultiple(int[] boxPattern, int gameCount) {
        List<List<Integer>> games = new ArrayList<>();
        for (int i = 0; i < gameCount; i++) {
            games.add(generateSingle(boxPattern));
        }
        return games;
    }
}

