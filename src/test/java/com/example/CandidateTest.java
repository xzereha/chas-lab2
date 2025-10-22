package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {
    @Test
    void constructor_validArguments_shouldCreateCandidate() {
        Candidate c = new Candidate("Anna", 30, "IT", 5);
        assertEquals("Anna", c.getName());
        assertEquals(30, c.getAge());
        assertEquals("IT", c.getIndustry());
        assertEquals(5, c.getYearsOfExperience());
    }

    @Test
    void constructor_nullName_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Candidate(null, 30, "IT", 5));
    }

    @Test
    void constructor_blankName_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Candidate(" ", 30, "IT", 5));
    }

    @Test
    void constructor_negativeAge_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Candidate("Anna", -1, "IT", 5));
    }

    @Test
    void constructor_nullIndustry_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Candidate("Anna", 30, null, 5));
    }

    @Test
    void constructor_blankIndustry_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Candidate("Anna", 30, " ", 5));
    }

    @Test
    void constructor_negativeExperience_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Candidate("Anna", 30, "IT", -1));
    }

    @Test
    void toString_shouldFormatCorrectly() {
        Candidate c = new Candidate("Anna", 30, "IT", 5);
        assertTrue(c.toString().contains("Anna"));
        assertTrue(c.toString().contains("30"));
        assertTrue(c.toString().contains("IT"));
        assertTrue(c.toString().contains("5"));
    }
}
