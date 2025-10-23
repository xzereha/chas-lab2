package com.example.commands;

import com.example.CandidateStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddCandidateCommandTest {
    @Test
    void execute_shouldAddCandidate() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("Anna\n30\nIT\n5\n");
        AddCandidateCommand cmd = new AddCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result);
        Mockito.verify(storage).addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") && c.getAge() == 30
                && c.getIndustry().equals("IT") && c.getYearsOfExperience() == 5));
    }

    @Test
    void execute_blankName_shouldRepromptOrReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("\nAnna\n30\nIT\n5\n");
        AddCandidateCommand cmd = new AddCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should eventually succeed after reprompt
        Mockito.verify(storage).addCandidate(Mockito.argThat(c -> c.getName().equals("Anna")));
    }

    @Test
    void execute_negativeAge_shouldRepromptOrReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("Anna\n-1\n30\nIT\n5\n");
        AddCandidateCommand cmd = new AddCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should eventually succeed after reprompt
        Mockito.verify(storage).addCandidate(Mockito.argThat(c -> c.getAge() == 30));
    }

    @Test
    void execute_blankIndustry_shouldRepromptOrReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("Anna\n30\n\nIT\n5\n");
        AddCandidateCommand cmd = new AddCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should eventually succeed after reprompt
        Mockito.verify(storage).addCandidate(Mockito.argThat(c -> c.getIndustry().equals("IT")));
    }

    @Test
    void execute_negativeExperience_shouldRepromptOrReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("Anna\n30\nIT\n-1\n5\n");
        AddCandidateCommand cmd = new AddCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertTrue(result); // Should eventually succeed after reprompt
        Mockito.verify(storage).addCandidate(Mockito.argThat(c -> c.getYearsOfExperience() == 5));
    }

    @Test
    void execute_streamEnds_shouldReturnFalse() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner(""); // No input
        AddCandidateCommand cmd = new AddCandidateCommand(storage, scanner);
        boolean result = cmd.execute();
        assertFalse(result); // Should return false if input stream ends
        Mockito.verify(storage, Mockito.never()).addCandidate(Mockito.any());
    }

    @Test
    void constructor_nullStorage_shouldThrow() {
        Scanner scanner = new Scanner("");
        Exception ex = assertThrows(NullPointerException.class, () -> new AddCandidateCommand(null, scanner));
        assertEquals("CandidateStorage must not be null", ex.getMessage());
    }

    @Test
    void constructor_nullScanner_shouldThrow() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Exception ex = assertThrows(NullPointerException.class, () -> new AddCandidateCommand(storage, null));
        assertEquals("Scanner must not be null", ex.getMessage());
    }
}
