package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CandidateRepository {
    private static final Logger logger = LoggerFactory.getLogger(CandidateRepository.class);
    private final List<Candidate> candidates = new ArrayList<>();

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        logger.info("Kandidat tillagd: {}", candidate);
    }

    public boolean removeCandidate(String name) {
        Optional<Candidate> candidateOpt = candidates.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
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

    public List<Candidate> getAllCandidates() {
        logger.info("Hämtar alla kandidater (antal: {})", candidates.size());
        return new ArrayList<>(candidates);
    }
}
