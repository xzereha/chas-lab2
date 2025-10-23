package com.example.commands;

import com.example.Candidate;
import com.example.CandidateStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterByExperienceCommandTest {
    @Test
    void execute_shouldFilterByExperience() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        List<Candidate> candidates = Arrays.asList(
                new Candidate("Anna", 30, "IT", 5),
                new Candidate("Bo", 40, "Ekonomi", 10));
        Mockito.when(storage.getAllCandidates()).thenReturn(candidates);
        Scanner scanner = new Scanner("10\n");
        FilterByExperienceCommand cmd = new FilterByExperienceCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result);
        Mockito.verify(storage).getAllCandidates();
    }

    @Test
    void execute_negativeExperience_shouldRepromptOrReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Mockito.when(storage.getAllCandidates()).thenReturn(java.util.Collections.emptyList());
        Scanner scanner = new Scanner("-1\n5\n");
        FilterByExperienceCommand cmd = new FilterByExperienceCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should eventually succeed after reprompt
        Mockito.verify(storage).getAllCandidates();
    }

    @Test
    void execute_streamEnds_shouldReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner(""); // No input
        FilterByExperienceCommand cmd = new FilterByExperienceCommand(storage, scanner);
        boolean result = cmd.execute();
        assertFalse(result); // Should return false if input stream ends
        Mockito.verify(storage, Mockito.never()).getAllCandidates();
    }

    @Test
    void execute_nonIntegerInput_shouldRepromptOrReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Mockito.when(storage.getAllCandidates()).thenReturn(java.util.Collections.emptyList());
        Scanner scanner = new Scanner("abc\n10\n");
        FilterByExperienceCommand cmd = new FilterByExperienceCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should eventually succeed after reprompt
        Mockito.verify(storage).getAllCandidates();
    }

    @Test
    void constructor_nullStorage_shouldThrow() {
        Scanner scanner = new Scanner("");
        Exception ex = assertThrows(NullPointerException.class, () -> new FilterByExperienceCommand(null, scanner));
        assertEquals("CandidateStorage must not be null", ex.getMessage());
    }

    @Test
    void constructor_nullScanner_shouldThrow() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Exception ex = assertThrows(NullPointerException.class, () -> new FilterByExperienceCommand(storage, null));
        assertEquals("Scanner must not be null", ex.getMessage());
    }
}
