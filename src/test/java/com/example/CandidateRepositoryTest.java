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
    void addCandidate_nullCandidate_shouldNotAdd() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(null);
        assertEquals(0, repo.getAllCandidates().size());
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

    @Test
    void removeCandidate_nullName_shouldReturnFalse() {
        CandidateRepository repo = new CandidateRepository();
        assertFalse(repo.removeCandidate(null));
    }

    @Test
    void removeCandidate_blankName_shouldReturnFalse() {
        CandidateRepository repo = new CandidateRepository();
        assertFalse(repo.removeCandidate("   "));
    }

    @Test
    void removeCandidate_notFound_shouldReturnFalse() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        assertFalse(repo.removeCandidate("Bo"));
    }

    @Test
    void getAllCandidates_shouldReturnCopy() {
        CandidateRepository repo = new CandidateRepository();
        Candidate c = new Candidate("Anna", 30, "IT", 5);
        repo.addCandidate(c);
        java.util.List<Candidate> list = repo.getAllCandidates();
        assertEquals(1, list.size());
        list.clear();
        assertEquals(1, repo.getAllCandidates().size());
    }
}
