package com.core.analyzer.service.analyze;

import com.core.analyzer.model.boxResult.BoxResult;
import com.core.analyzer.domain.draw.DrawResult;
import com.core.analyzer.domain.pattern.DrawWithBoxPattern;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FixDataPolicy implements HandleData {

    private final Function<Integer, Integer> classifyByOrbitalGroup = number -> {
        if (number <= 2) return 1;
        else if (number <= 10) return 2;
        else if (number <= 18) return 3;
        else if (number <= 30) return 4;
        else if (number <= 32) return 5;
        else if (number <= 36) return 6;
        else if (number <= 45) return 7;
        else return -1;
    };

    @Override
    public List<DrawResult> readData(String rowData) {
        return Arrays.stream(rowData.split("\n"))
                .map(String::trim)
                .map(line -> line.split("\\s+"))
                .filter(numbers -> numbers.length == 7)
                .map(numbers -> new DrawResult(
                        Integer.parseInt(numbers[0]),
                        Integer.parseInt(numbers[1]),
                        Integer.parseInt(numbers[2]),
                        Integer.parseInt(numbers[3]),
                        Integer.parseInt(numbers[4]),
                        Integer.parseInt(numbers[5]),
                        Integer.parseInt(numbers[6])
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<BoxResult> readBoxData(List<DrawResult> drawResults) {
        List<BoxResult> resultList = new ArrayList<>();

        for (DrawResult dr : drawResults) {
            int[] numbers = {
                    dr.getNumber1(), dr.getNumber2(), dr.getNumber3(),
                    dr.getNumber4(), dr.getNumber5(), dr.getNumber6()
            };

            int[] box = new int[8];
            Map<Integer, List<Integer>> boxMap = new HashMap<>();

            for (int num : numbers) {
                int group = classifyByOrbitalGroup.apply(num);
                if (group >= 1 && group <= 7) {
                    box[group]++;
                    boxMap.computeIfAbsent(group, k -> new ArrayList<>()).add(num);
                } else {
                    System.err.println("❌ 분류되지 않은 숫자 발생: " + num);
                }
            }

            double[] means = new double[8];
            double[] stds = new double[8];

            for (int i = 1; i <= 7; i++) {
                List<Integer> values = boxMap.getOrDefault(i, List.of());
                if (!values.isEmpty()) {
                    double mean = values.stream().mapToInt(v -> v).average().orElse(0);
                    double variance = values.stream().mapToDouble(v -> Math.pow(v - mean, 2)).average().orElse(0);
                    means[i] = mean;
                    stds[i] = Math.sqrt(variance);
                }
            }

            BoxResult boxResult = new BoxResult(
                    dr.getIdx(),
                    box[1], box[2], box[3], box[4], box[5], box[6], box[7]
            );
            boxResult.setBoxMeans(means);
            boxResult.setBoxStds(stds);
            resultList.add(boxResult);
        }

        return resultList;
    }

    @Override
    public List<DrawResult> filterBoxCheck(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check) {
        Set<Integer> matchingIdxSet = boxResults.stream()
                .filter(box -> IntStream.range(0, 7).allMatch(i -> box.getBoxByIndex(i + 1) == check[i]))
                .map(BoxResult::getIdx)
                .collect(Collectors.toSet());

        return drawResults.stream()
                .filter(dr -> matchingIdxSet.contains(dr.getIdx()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DrawResult> fixedBoxData(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check) {
        Set<Integer> targetIdxSet = boxResults.stream()
                .filter(box -> IntStream.range(0, 7).allMatch(i -> box.getBoxByIndex(i + 1) == check[i]))
                .map(box -> box.getIdx() + 1)
                .filter(idx -> idx > 0)
                .collect(Collectors.toSet());

        return drawResults.stream()
                .filter(dr -> targetIdxSet.contains(dr.getIdx()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DrawWithBoxPattern> mapToBoxPatternResult(List<DrawResult> drawResults, List<BoxResult> boxResults) {
        Map<Integer, BoxResult> boxMap = boxResults.stream()
                .collect(Collectors.toMap(BoxResult::getIdx, b -> b));

        return drawResults.stream()
                .map(dr -> {
                    BoxResult box = boxMap.get(dr.getIdx());
                    if (box == null) return null;
                    return new DrawWithBoxPattern(
                            dr.getIdx(),
                            Arrays.asList(
                                    dr.getNumber1(), dr.getNumber2(), dr.getNumber3(),
                                    dr.getNumber4(), dr.getNumber5(), dr.getNumber6()
                            ),
                            Arrays.asList(
                                    box.getBox1(), box.getBox2(), box.getBox3(),
                                    box.getBox4(), box.getBox5(), box.getBox6(), box.getBox7()
                            ),
                            box.getBoxMeans(),
                            box.getBoxStds()
                    );
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> extractLastBoxPattern(List<DrawResult> drawResults, List<BoxResult> boxResults) {
        if (drawResults.isEmpty()) return Collections.emptyList();

        int lastIdx = drawResults.get(drawResults.size() - 1).getIdx();

        return boxResults.stream()
                .filter(b -> b.getIdx() == lastIdx)
                .findFirst()
                .map(box -> Arrays.asList(
                        box.getBox1(), box.getBox2(), box.getBox3(),
                        box.getBox4(), box.getBox5(), box.getBox6(), box.getBox7()
                ))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Integer> findMatchingIdxByBoxPattern(List<BoxResult> boxResults, List<Integer> boxPattern) {
        if (boxPattern == null || boxPattern.size() != 7) return Collections.emptyList();

        return boxResults.stream()
                .filter(box ->
                        box.getBox1() == boxPattern.get(0) &&
                                box.getBox2() == boxPattern.get(1) &&
                                box.getBox3() == boxPattern.get(2) &&
                                box.getBox4() == boxPattern.get(3) &&
                                box.getBox5() == boxPattern.get(4) &&
                                box.getBox6() == boxPattern.get(5) &&
                                box.getBox7() == boxPattern.get(6)
                )
                .map(BoxResult::getIdx)
                .sorted()
                .collect(Collectors.toList());
    }

    public void printLastDrawAndBoxPattern(List<DrawResult> drawResults, List<BoxResult> boxResults) {
        if (drawResults.isEmpty()) {
            System.out.println("❌ drawResults가 비어있습니다.");
            return;
        }

        DrawResult lastDraw = drawResults.get(drawResults.size() - 1);
        int lastIdx = lastDraw.getIdx();

        System.out.println("🎯 최신 회차 번호: " +
                lastDraw.getNumber1() + ", " +
                lastDraw.getNumber2() + ", " +
                lastDraw.getNumber3() + ", " +
                lastDraw.getNumber4() + ", " +
                lastDraw.getNumber5() + ", " +
                lastDraw.getNumber6()
        );

        boxResults.stream()
                .filter(b -> b.getIdx() == lastIdx)
                .findFirst()
                .ifPresentOrElse(
                        box -> System.out.println("📦 최신 박스 패턴: " + Arrays.asList(
                                box.getBox1(), box.getBox2(), box.getBox3(),
                                box.getBox4(), box.getBox5(), box.getBox6(), box.getBox7()
                        )),
                        () -> System.out.println("❌ 해당 idx(" + lastIdx + ")의 BoxResult 없음")
                );
    }

    @Override
    public Map.Entry<List<Integer>, List<Integer>> findRarestBoxPattern(List<DrawResult> drawResults, List<BoxResult> boxResults) {
        int maxTryBack = 10;
        int recentIdx = drawResults.get(drawResults.size() - 1).getIdx();

        for (int offset = 0; offset <= maxTryBack; offset++) {
            int targetIdx = recentIdx - offset;

            Optional<DrawResult> maybeTargetDraw = drawResults.stream()
                    .filter(dr -> dr.getIdx() == targetIdx)
                    .findFirst();

            if (maybeTargetDraw.isEmpty()) continue;

            List<Integer> pattern = extractLastBoxPattern(List.of(maybeTargetDraw.get()), boxResults);

            List<Integer> matchingIdxList = findMatchingIdxByBoxPattern(
                    boxResults.stream()
                            .filter(b -> b.getIdx() < recentIdx) // 최신 제외
                            .collect(Collectors.toList()),
                    pattern);

            if (matchingIdxList.size() > 0 && matchingIdxList.size() <= 3) {
                System.out.println("🎯 희소 패턴 발견 → 기준 회차: " + targetIdx + ", 등장 횟수: " + matchingIdxList.size());
                return Map.entry(pattern, matchingIdxList);
            }
        }

        System.out.println("❌ 희소한 패턴을 찾지 못했습니다.");
        return Map.entry(Collections.emptyList(), Collections.emptyList());
    }

    public Map.Entry<List<Integer>, List<Integer>> findMostFrequentBoxPattern(List<DrawResult> drawResults, List<BoxResult> boxResults) {
        int maxTryBack = 10;
        int recentIdx = drawResults.get(drawResults.size() - 1).getIdx();

        Map<List<Integer>, List<Integer>> candidateMap = new HashMap<>();

        for (int offset = 0; offset <= maxTryBack; offset++) {
            int targetIdx = recentIdx - offset;

            Optional<DrawResult> maybeTargetDraw = drawResults.stream()
                    .filter(dr -> dr.getIdx() == targetIdx)
                    .findFirst();

            if (maybeTargetDraw.isEmpty()) continue;

            // 해당 회차의 박스 패턴 추출
            List<Integer> pattern = extractLastBoxPattern(List.of(maybeTargetDraw.get()), boxResults);

            // 최신 회차보다 이전의 동일 패턴 회차들 탐색
            List<Integer> matchingIdxList = findMatchingIdxByBoxPattern(
                    boxResults.stream()
                            .filter(b -> b.getIdx() < recentIdx) // 최신 회차 제외
                            .collect(Collectors.toList()),
                    pattern);

            if (!matchingIdxList.isEmpty()) {
                candidateMap.put(pattern, matchingIdxList);
            }
        }

        if (candidateMap.isEmpty()) {
            System.out.println("❌ 자주 등장한 패턴을 찾지 못했습니다.");
            return Map.entry(Collections.emptyList(), Collections.emptyList());
        }

        // 가장 많이 등장한 패턴 선택
        Map.Entry<List<Integer>, List<Integer>> maxEntry = candidateMap.entrySet().stream()
                .max(Comparator.comparingInt(e -> e.getValue().size()))
                .orElseThrow();

        System.out.println("🎯 자주 등장한 패턴 발견 → 기준 회차: " + maxEntry.getValue().get(maxEntry.getValue().size() - 1) +
                ", 등장 횟수: " + maxEntry.getValue().size());
        return maxEntry;
    }


}

