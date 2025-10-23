package com.example;

import java.util.List;

/**
 * Filters candidates by name (case-insensitive, trimmed).
 */
public class NameFilter implements CandidateFilter {
    private final String name;

    public NameFilter(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name for filtering must not be null or empty");
        }
        this.name = name;
    }

    @Override
    public List<Candidate> filter(List<Candidate> candidates) {
        return candidates.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .toList();
    }
}
