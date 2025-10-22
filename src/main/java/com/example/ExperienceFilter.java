package com.example;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceFilter implements CandidateFilter {
    private final int minYears;

    public ExperienceFilter(int minYears) {
        this.minYears = minYears;
    }

    @Override
    public List<Candidate> filter(List<Candidate> candidates) {
        return candidates.stream()
                .filter(c -> c.getYearsOfExperience() >= minYears)
                .collect(Collectors.toList());
    }
}
