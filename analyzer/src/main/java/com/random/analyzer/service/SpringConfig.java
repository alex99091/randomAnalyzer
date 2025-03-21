package com.random.analyzer.service;

import com.random.analyzer.repository.DrawResultRepository;
import com.random.analyzer.repository.JpaMemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public DrawResultService drawResultService() {
        return new DrawResultService(drawResultRepository()); // ✅ 올바른 의존성 주입
    }

    @Bean
    public DrawResultRepository drawResultRepository() {
        return new JpaMemberRepository(em); // ✅ EntityManager 주입 필요
    }
}
