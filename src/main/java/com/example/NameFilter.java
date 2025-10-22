package com.example;

import java.util.List;

/**
 * Filters candidates by name (case-insensitive, trimmed).
 */
public class NameFilter implements CandidateFilter {
    private final String name;

    public NameFilter(String name) {
        this.name = name != null ? name.trim() : "";
    }

    @Override
    public List<Candidate> filter(List<Candidate> candidates) {
        return candidates.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .toList();
    }
}
