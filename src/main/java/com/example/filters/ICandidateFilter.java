package com.example.filters;

import java.util.List;

import com.example.models.Candidate;

public interface ICandidateFilter {
    List<Candidate> filter(List<Candidate> candidates);
}
