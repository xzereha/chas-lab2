package com.example;

import java.util.List;

public interface CandidateFilter {
    List<Candidate> filter(List<Candidate> candidates);
}
