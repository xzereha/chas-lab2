package com.example.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {
    @Test
    void execute_called_returnsFalse() {
        ExitCommand cmd = new ExitCommand();
        boolean result = cmd.execute();
        assertFalse(result);
    }
}
