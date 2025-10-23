package com.example;

import java.util.List;
import java.util.stream.Collectors;

public class IndustryFilter implements CandidateFilter {
    private final String industry;

    public IndustryFilter(String industry) {
        if (industry == null || industry.trim().isEmpty()) {
            throw new IllegalArgumentException("Industry for filtering must not be null or empty");
        }
        this.industry = industry;
    }

    @Override
    public List<Candidate> filter(List<Candidate> candidates) {
        return candidates.stream()
                .filter(c -> c.getIndustry().equalsIgnoreCase(industry))
                .collect(Collectors.toList());
    }
}
