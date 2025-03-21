package com.random.analyzer.repository;

import com.random.analyzer.model.DrawResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrawResultRepositoryImpl implements DrawResultRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public DrawResult saveEach(List<DrawResult> drawResults) {
        DrawResult lastSaved = null;
        for (DrawResult result : drawResults) {
            em.persist(result);
            lastSaved = result;
        }
        return lastSaved;
    }

    @Override
    public DrawResult saveRow(List<DrawResult> drawResults) {
        if (drawResults == null || drawResults.isEmpty()) return null;
        DrawResult row = drawResults.get(0);
        em.persist(row);
        return row;
    }

}