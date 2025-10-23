package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IndustryFilterTest {
    @Test
    void filter_validIndustry_returnsMatchingCandidates() {
        Candidate c1 = new Candidate("Anna", 30, "IT", 5);
        Candidate c2 = new Candidate("Bo", 40, "Ekonomi", 10);
        Candidate c3 = new Candidate("Cecilia", 25, "IT", 2);
        List<Candidate> candidates = List.of(c1, c2, c3);
        IndustryFilter filter = new IndustryFilter("IT");
        List<Candidate> result = filter.filter(candidates);
        assertEquals(2, result.size());
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c3));
    }

    @Test
    void constructor_nullIndustry_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new IndustryFilter(null));
        assertEquals("Industry for filtering must not be null or empty", ex.getMessage());
    }

    @Test
    void constructor_emptyIndustry_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new IndustryFilter("   "));
        assertEquals("Industry for filtering must not be null or empty", ex.getMessage());
    }
}
