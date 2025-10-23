package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AlphabeticalNameSorterTest {
    @Test
    void filter_unsortedList_returnsAlphabeticallySorted() {
        Candidate c1 = new Candidate("Bo", 40, "Ekonomi", 10);
        Candidate c2 = new Candidate("Anna", 30, "IT", 5);
        Candidate c3 = new Candidate("Cecilia", 25, "IT", 2);
        List<Candidate> candidates = List.of(c1, c2, c3);
        AlphabeticalNameSorter sorter = new AlphabeticalNameSorter();
        List<Candidate> result = sorter.filter(candidates);
        assertEquals(List.of(c2, c1, c3), result);
    }

    @Test
    void filter_emptyList_returnsEmptyList() {
        AlphabeticalNameSorter sorter = new AlphabeticalNameSorter();
        List<Candidate> result = sorter.filter(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void filter_sameName_isStable() {
        Candidate c1 = new Candidate("Anna", 30, "IT", 5);
        Candidate c2 = new Candidate("Anna", 25, "Ekonomi", 2);
        List<Candidate> candidates = List.of(c1, c2);
        AlphabeticalNameSorter sorter = new AlphabeticalNameSorter();
        List<Candidate> result = sorter.filter(candidates);
        assertEquals(List.of(c1, c2), result);
    }
}
