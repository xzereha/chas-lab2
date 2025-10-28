package com.example.filters;

import java.util.List;
import java.util.stream.Collectors;

import com.example.models.Candidate;

public class ExperienceFilter implements ICandidateFilter {
    private final int minYears;

    public ExperienceFilter(int minYears) {
        if (minYears < 0) {
            throw new IllegalArgumentException("Years of experience for filtering must not be negative");
        }
        this.minYears = minYears;
    }

    @Override
    public List<Candidate> filter(List<Candidate> candidates) {
        return candidates.stream()
                .filter(c -> c.getYearsOfExperience() >= minYears)
                .collect(Collectors.toList());
    }
}
