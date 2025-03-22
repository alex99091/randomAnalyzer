package com.core.analyzer.analyze;

import com.core.analyzer.boxResult.BoxResult;
import com.core.analyzer.drawResult.DrawResult;
import com.core.analyzer.dto.DrawWithBoxPattern;

import java.util.*;
import java.util.stream.Collectors;

public class FixDataPolicy implements HandleData{

    @Override
    public List<DrawResult> readData(String rowData) {
        List<DrawResult> results = new ArrayList<>();
        String[] lines = rowData.split("\n");
        for (String line : lines) {
            String[] numbers = line.trim().split("\\s+");
            if (numbers.length != 7) continue;
            results.add(new DrawResult(
                    Integer.parseInt(numbers[0]),
                    Integer.parseInt(numbers[1]),
                    Integer.parseInt(numbers[2]),
                    Integer.parseInt(numbers[3]),
                    Integer.parseInt(numbers[4]),
                    Integer.parseInt(numbers[5]),
                    Integer.parseInt(numbers[6])
            ));
        }
        return results;
    }

    @Override
    public List<BoxResult> readBoxData(List<DrawResult> drawResults) {
        List<BoxResult> resultList = new ArrayList<>();

        for (DrawResult dr : drawResults) {
            int[] numbers = {
                    dr.getNumber1(), dr.getNumber2(), dr.getNumber3(),
                    dr.getNumber4(), dr.getNumber5(), dr.getNumber6()
            };

            int box1 = 0, box2 = 0, box3 = 0, box4 = 0, box5 = 0;

            for (int num : numbers) {
                if (num >= 1 && num <= 10) box1++;
                else if (num <= 20) box2++;
                else if (num <= 30) box3++;
                else if (num <= 40) box4++;
                else if (num <= 50) box5++;
            }

            resultList.add(new BoxResult(dr.getIdx(), box1, box2, box3, box4, box5));
        }

        return resultList;
    }

    @Override
    public List<DrawResult> filterBoxCheck(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check) {
        List<Integer> matchingIdxList = new ArrayList<>();

        for (BoxResult box : boxResults) {
            if (box.getBox1() == check[0] &&
                    box.getBox2() == check[1] &&
                    box.getBox3() == check[2] &&
                    box.getBox4() == check[3] &&
                    box.getBox5() == check[4]) {
                matchingIdxList.add(box.getIdx());
            }
        }

        return drawResults.stream()
                .filter(dr -> matchingIdxList.contains(dr.getIdx()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DrawResult> fixedBoxData(List<DrawResult> drawResults, List<BoxResult> boxResults, int[] check) {
        List<Integer> targetIdxList = new ArrayList<>();

        for (BoxResult box : boxResults) {
            if (box.getBox1() == check[0] &&
                    box.getBox2() == check[1] &&
                    box.getBox3() == check[2] &&
                    box.getBox4() == check[3] &&
                    box.getBox5() == check[4]) {
                int previousIdx = box.getIdx() - 1;
                if (previousIdx > 0) { // 음수나 0 방지
                    targetIdxList.add(previousIdx);
                }
            }
        }

        return drawResults.stream()
                .filter(dr -> targetIdxList.contains(dr.getIdx()))
                .collect(Collectors.toList());
    }

    public List<DrawWithBoxPattern> mapToBoxPatternResult(List<DrawResult> drawResults, List<BoxResult> boxResults) {
        Map<Integer, BoxResult> boxMap = boxResults.stream()
                .collect(Collectors.toMap(BoxResult::getIdx, b -> b));

        List<DrawWithBoxPattern> result = new ArrayList<>();

        for (DrawResult dr : drawResults) {
            BoxResult box = boxMap.get(dr.getIdx());
            if (box != null) {
                List<Integer> numbers = Arrays.asList(
                        dr.getNumber1(), dr.getNumber2(), dr.getNumber3(),
                        dr.getNumber4(), dr.getNumber5(), dr.getNumber6()
                );
                List<Integer> boxPattern = Arrays.asList(
                        box.getBox1(), box.getBox2(), box.getBox3(),
                        box.getBox4(), box.getBox5()
                );
                result.add(new DrawWithBoxPattern(dr.getIdx(), numbers, boxPattern));
            }
        }

        return result;
    }

}
