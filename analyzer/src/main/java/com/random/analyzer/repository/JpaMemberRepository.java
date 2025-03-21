package com.random.analyzer.repository;

import com.random.analyzer.model.DrawResult;
import com.random.analyzer.model.NumberRangeCount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JpaMemberRepository implements DrawResultRepository {

    @PersistenceContext
    private final EntityManager em;
    private static int drawIdx = 0;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public DrawResult saveEach(List<DrawResult> drawResults) {
        DrawResult last = null;
        for (DrawResult result : drawResults) {
            em.merge(result); // ✅ persist → merge
            last = result;
        }
        return last;
    }


    @Override
    public DrawResult saveRow(List<DrawResult> drawResults) {
        int[] counts = new int[5]; // [1~10, 11~20, ..., 41~50]

        for (DrawResult result : drawResults) {
            int[] numbers = {
                    result.getNumber1(),
                    result.getNumber2(),
                    result.getNumber3(),
                    result.getNumber4(),
                    result.getNumber5(),
                    result.getNumber6()
            };

            for (int num : numbers) {
                int index = (num - 1) / 10;
                if (index >= 0 && index < 5) {
                    counts[index]++;
                }
            }
        }

        NumberRangeCount numberRangeCount = new NumberRangeCount();
        numberRangeCount.setBox1(counts[0]);
        numberRangeCount.setBox2(counts[1]);
        numberRangeCount.setBox3(counts[2]);
        numberRangeCount.setBox4(counts[3]);
        numberRangeCount.setBox5(counts[4]);

        em.persist(numberRangeCount); // 또는 repository.save(rangeCount)

        return null;
    }



}
