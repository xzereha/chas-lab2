package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ExperienceFilterTest {
    @Test
    void filter_shouldReturnCandidatesWithMinYears() {
        Candidate c1 = new Candidate("Anna", 30, "IT", 5);
        Candidate c2 = new Candidate("Bo", 40, "Ekonomi", 10);
        Candidate c3 = new Candidate("Cecilia", 25, "IT", 2);
        List<Candidate> candidates = List.of(c1, c2, c3);
        ExperienceFilter filter = new ExperienceFilter(5);
        List<Candidate> result = filter.filter(candidates);
        assertEquals(2, result.size());
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c2));
    }

    @Test
    void constructor_negativeYears_shouldThrow() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new ExperienceFilter(-1));
        assertEquals("Years of experience for filtering must not be negative", ex.getMessage());
    }
}
