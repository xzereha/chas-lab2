package com.example;

import com.example.commands.Command;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SubMenuTest {
    @Test
    void showAndHandle_validInput_executesCorrectCommand() {
        Command cmd1 = Mockito.mock(Command.class);
        Command cmd2 = Mockito.mock(Command.class);
        Mockito.when(cmd1.menuText()).thenReturn("Option 1");
        Mockito.when(cmd2.menuText()).thenReturn("Option 2");
        Mockito.when(cmd1.execute()).thenReturn(true);
        Mockito.when(cmd2.execute()).thenReturn(true);
        Map<String, Command> options = new LinkedHashMap<>();
        options.put("1", cmd1);
        options.put("2", cmd2);
        Scanner scanner = new Scanner("2\n");
        SubMenu submenu = new SubMenu("Header", scanner);
        options.forEach(submenu::with);
        boolean result = submenu.showAndHandle();
        assertTrue(result);
        Mockito.verify(cmd2).execute();
        Mockito.verify(cmd1, Mockito.never()).execute();
    }

    @Test
    void showAndHandle_streamEnds_exitsGracefully() {
        Command cmd1 = Mockito.mock(Command.class);
        Mockito.when(cmd1.menuText()).thenReturn("Option 1");
        Map<String, Command> options = new LinkedHashMap<>();
        options.put("1", cmd1);
        Scanner scanner = new Scanner(""); // No input
        SubMenu submenu = new SubMenu("Header", scanner);
        options.forEach(submenu::with);
        boolean result = submenu.showAndHandle();
        assertFalse(result); // Should not throw, but not execute any command
        Mockito.verify(cmd1, Mockito.never()).execute();
    }

    @Test
    void showAndHandle_commandReturnsFalse_exits() {
        Command cmd1 = Mockito.mock(Command.class);
        Mockito.when(cmd1.menuText()).thenReturn("Option 1");
        Mockito.when(cmd1.execute()).thenReturn(false);
        Map<String, Command> options = new LinkedHashMap<>();
        options.put("1", cmd1);
        Scanner scanner = new Scanner("1\n");
        SubMenu submenu = new SubMenu("Header", scanner);
        options.forEach(submenu::with);
        boolean result = submenu.showAndHandle();
        assertFalse(result); // Should return false if command returns false
        Mockito.verify(cmd1).execute();
    }
}
