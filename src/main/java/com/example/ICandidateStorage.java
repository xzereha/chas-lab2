package com.example;

import java.util.List;

import com.example.models.Candidate;

public interface ICandidateStorage {
    /**
     * Adds a candidate to the storage.
     */
    void addCandidate(Candidate candidate);

    /**
     * Removes a candidate by name. Returns true if removed.
     */
    boolean removeCandidate(String name);

    /**
     * Returns all candidates in storage.
     */
    List<Candidate> getAllCandidates();
}
