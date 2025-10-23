package com.example.commands;

import com.example.Candidate;
import com.example.CandidateStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowCandidatesCommandTest {
    @Test
    void execute_candidatesExist_showsCandidates() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        List<Candidate> candidates = Arrays.asList(
                new Candidate("Anna", 30, "IT", 5),
                new Candidate("Bo", 40, "Ekonomi", 10));
        Mockito.when(storage.getAllCandidates()).thenReturn(candidates);
        Scanner scanner = new Scanner("");
        ShowCandidatesCommand cmd = new ShowCandidatesCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result);
        Mockito.verify(storage).getAllCandidates();
    }

    @Test
    void execute_streamEnds_returnsTrue() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Mockito.when(storage.getAllCandidates()).thenReturn(java.util.Collections.emptyList());
        Scanner scanner = new Scanner(""); // No input needed
        ShowCandidatesCommand cmd = new ShowCandidatesCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should succeed even if no candidates
        Mockito.verify(storage).getAllCandidates();
    }

    @Test
    void constructor_nullStorage_throwsException() {
        Scanner scanner = new Scanner("");
        Exception ex = assertThrows(NullPointerException.class, () -> new ShowCandidatesCommand(null, scanner));
        assertEquals("CandidateStorage must not be null", ex.getMessage());
    }

    @Test
    void constructor_nullScanner_throwsException() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Exception ex = assertThrows(NullPointerException.class, () -> new ShowCandidatesCommand(storage, null));
        assertEquals("Scanner must not be null", ex.getMessage());
    }
}
