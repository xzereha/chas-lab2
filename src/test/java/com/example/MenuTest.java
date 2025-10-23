package com.example;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void menuConstructor_nullStorage_throwsException() {
        Scanner scanner = new Scanner("");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Menu(null, scanner));
        assertEquals("CandidateStorage must not be null", ex.getMessage());
    }

    @Test
    void menuConstructor_nullScanner_throwsException() {
        CandidateStorage storage = Mockito.mock(CandidateStorage.class);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Menu(storage, null));
        assertEquals("Scanner must not be null", ex.getMessage());
    }
}
