package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Repository for managing Candidate objects in-memory.
 * Provides add, remove, and retrieval operations.
 */
public class CandidateRepository implements CandidateStorage {
    private static final Logger logger = LoggerFactory.getLogger(CandidateRepository.class);
    private final List<Candidate> candidates = new ArrayList<>();

    /**
     * Adds a candidate to the repository.
     * Ignores null input.
     * 
     * @param candidate Candidate to add
     */
    public void addCandidate(Candidate candidate) {
        if (candidate == null) {
            logger.warn("Försökte lägga till null-kandidat.");
            return;
        }
        candidates.add(candidate);
        logger.info("Kandidat tillagd: {}", candidate);
    }

    /**
     * Removes a candidate by name.
     * Returns true if removed, false if not found or name is null/empty.
     * 
     * @param name Name of candidate to remove
     * @return true if candidate was removed
     */
    public boolean removeCandidate(String name) {
        if (name == null || name.isBlank()) {
            logger.warn("Ogiltigt namn för borttagning: '{}'.", name);
            return false;
        }
        Optional<Candidate> candidateOpt = candidates.stream()
                .filter(c -> name.equalsIgnoreCase(c.getName()))
                .findFirst();
        candidateOpt.ifPresent(c -> {
            candidates.remove(c);
            logger.info("Kandidat borttagen: {}", c);
        });
        if (!candidateOpt.isPresent()) {
            logger.warn("Kandidat med namn '{}' hittades inte.", name);
        }
        return candidateOpt.isPresent();
    }

    /**
     * Returns a copy of all candidates in the repository.
     * 
     * @return List of candidates
     */
    public List<Candidate> getAllCandidates() {
        logger.info("Hämtar alla kandidater (antal: {})", candidates.size());
        return new ArrayList<>(candidates);
    }
}
