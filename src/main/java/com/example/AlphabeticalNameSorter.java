package com.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticalNameSorter implements CandidateFilter {
    @Override
    public List<Candidate> filter(List<Candidate> candidates) {
        return candidates.stream()
                .sorted(Comparator.comparing(Candidate::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
}
