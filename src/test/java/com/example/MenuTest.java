package com.example;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

import org.mockito.Mockito;

import com.example.menu.Menu;

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
        ICandidateStorage storage = Mockito.mock(ICandidateStorage.class);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Menu(storage, null));
        assertEquals("Scanner must not be null", ex.getMessage());
    }

    @Test
    void menuConstructor_validParameters_createsMenu() {
        ICandidateStorage storage = Mockito.mock(ICandidateStorage.class);
        Scanner scanner = new Scanner("");
        Menu menu = new Menu(storage, scanner);
        assertNotNull(menu);
    }

    @Test
    void menu_run_loopsUntilStreamEnds() {
        ICandidateStorage storage = Mockito.mock(ICandidateStorage.class);
        // Provide two valid menu choices, then stream ends
        Scanner scanner = new Scanner("1\n1\n");
        Menu menu = new Menu(storage, scanner);
        assertDoesNotThrow(menu::run);
        // The test passes if no exception is thrown and the loop exits when input ends
    }
}
