package com.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.filters.ICandidateFilter;
import com.example.models.Candidate;

public class AlphabeticalNameSorter implements ICandidateFilter {
    @Override
    public List<Candidate> filter(List<Candidate> candidates) {
        return candidates.stream()
                .sorted(Comparator.comparing(Candidate::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
}
