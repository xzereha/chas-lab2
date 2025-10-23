package com.example.commands;

import com.example.CandidateStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RemoveCandidateCommandTest {
    @Test
    void execute_shouldRemoveCandidate() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("Anna\n");
        RemoveCandidateCommand cmd = new RemoveCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result);
        Mockito.verify(storage).removeCandidate("Anna");
    }

    @Test
    void execute_blankName_shouldRepromptOrReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Mockito.when(storage.removeCandidate("Anna")).thenReturn(true);
        Scanner scanner = new Scanner("\nAnna\n");
        RemoveCandidateCommand cmd = new RemoveCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should eventually succeed after reprompt
        Mockito.verify(storage).removeCandidate("Anna");
    }

    @Test
    void execute_streamEnds_shouldReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner(""); // No input
        RemoveCandidateCommand cmd = new RemoveCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertFalse(result); // Should return false if input stream ends
        Mockito.verify(storage, Mockito.never()).removeCandidate(Mockito.any());
    }

    @Test
    void constructor_nullStorage_shouldThrow() {
        Scanner scanner = new Scanner("");
        Exception ex = assertThrows(NullPointerException.class, () -> new RemoveCandidateCommand(null, scanner));
        assertEquals("CandidateStorage must not be null", ex.getMessage());
    }

    @Test
    void constructor_nullScanner_shouldThrow() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Exception ex = assertThrows(NullPointerException.class, () -> new RemoveCandidateCommand(storage, null));
        assertEquals("Scanner must not be null", ex.getMessage());
    }
}
