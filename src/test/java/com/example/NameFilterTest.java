package com.example;

import org.junit.jupiter.api.Test;

import com.example.filters.NameFilter;

import static org.junit.jupiter.api.Assertions.*;

class NameFilterTest {
    @Test
    void constructor_nullName_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new NameFilter(null));
        assertEquals("Name for filtering must not be null or empty", ex.getMessage());
    }

    @Test
    void constructor_emptyName_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new NameFilter("   "));
        assertEquals("Name for filtering must not be null or empty", ex.getMessage());
    }
}
