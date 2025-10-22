package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class CandidateRepositoryTest {
    @Test
    void addCandidate_shouldIncreaseSize() {
        CandidateRepository repo = new CandidateRepository();
        Candidate candidate = Mockito.mock(Candidate.class);
        repo.addCandidate(candidate);
        assertEquals(1, repo.getAllCandidates().size());
    }

    @Test
    void removeCandidate_shouldRemoveByName() {
        CandidateRepository repo = new CandidateRepository();
        Candidate candidate = new Candidate("Anna", 30, "IT", 5);
        repo.addCandidate(candidate);
        boolean removed = repo.removeCandidate("Anna");
        assertTrue(removed);
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void removeCandidate_shouldReturnFalseIfNotFound() {
        CandidateRepository repo = new CandidateRepository();
        boolean removed = repo.removeCandidate("Nonexistent");
        assertFalse(removed);
    }
}
