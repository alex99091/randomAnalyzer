package com.core.analyzer.engine.generate;

import java.util.*;

public class Generator {

    private static final Map<Integer, int[]> BOX_RANGES = Map.of(
            0, new int[]{1, 2},     // 1s
            1, new int[]{3, 10},    // 2s, 2p
            2, new int[]{11, 18},   // 3s, 3p
            3, new int[]{19, 30},   // 3d, 4s, 4p
            4, new int[]{31, 32},   // 5p
            5, new int[]{33, 36},   // 4p
            6, new int[]{37, 45}    // 5s, 4d
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

            int rangeSize = end - start + 1;
            if (count > rangeSize) {
                throw new IllegalArgumentException("Box " + (i + 1) + "의 숫자 수(" + count + ")가 범위 크기(" + rangeSize + ")보다 큽니다.");
            }

            Set<Integer> boxNumbers = new HashSet<>();
            while (boxNumbers.size() < count) {
                int num = random.nextInt(end - start + 1) + start;
                boxNumbers.add(num);
            }

            result.addAll(boxNumbers);
        }

        List<Integer> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        return sorted;
    }

    // N게임 자동 생성
    public List<List<Integer>> generateMultiple(int[] boxPattern, int gameCount) {
        List<List<Integer>> games = new ArrayList<>();
        for (int i = 0; i < gameCount; i++) {
            games.add(generateSingle(boxPattern));
        }
        return games;
    }
}
