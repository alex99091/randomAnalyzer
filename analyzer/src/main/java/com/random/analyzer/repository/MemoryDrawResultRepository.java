package com.random.analyzer.repository;

import com.random.analyzer.model.DrawResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryDrawResultRepository implements DrawResultRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public DrawResult saveEach(List<DrawResult> drawResults) {
        return null;
    }

    @Override
    public DrawResult saveRow(List<DrawResult> drawResults) {
        return null;
    }
}
