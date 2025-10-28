package com.example;

import com.example.commands.ICommand;
import com.example.menu.SubMenu;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SubMenuTest {
    @Test
    void showAndHandle_invalidChoice_shouldHandleGracefully() {
        ICommand cmd1 = Mockito.mock(ICommand.class);
        Mockito.when(cmd1.menuText()).thenReturn("Option 1");
        Map<String, ICommand> options = new LinkedHashMap<>();
        options.put("1", cmd1);
        Scanner scanner = new Scanner("9\n"); // invalid choice
        SubMenu submenu = new SubMenu("Header", scanner);
        options.forEach(submenu::with);
        boolean result = submenu.showAndHandle();
        assertTrue(result); // Should return true and not execute any command
        Mockito.verify(cmd1, Mockito.never()).execute();
    }

    @Test
    void showAndHandle_validInput_executesCorrectCommand() {
        ICommand cmd1 = Mockito.mock(ICommand.class);
        ICommand cmd2 = Mockito.mock(ICommand.class);
        Mockito.when(cmd1.menuText()).thenReturn("Option 1");
        Mockito.when(cmd2.menuText()).thenReturn("Option 2");
        Mockito.when(cmd1.execute()).thenReturn(true);
        Mockito.when(cmd2.execute()).thenReturn(true);
        Map<String, ICommand> options = new LinkedHashMap<>();
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
        ICommand cmd1 = Mockito.mock(ICommand.class);
        Mockito.when(cmd1.menuText()).thenReturn("Option 1");
        Map<String, ICommand> options = new LinkedHashMap<>();
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
        ICommand cmd1 = Mockito.mock(ICommand.class);
        Mockito.when(cmd1.menuText()).thenReturn("Option 1");
        Mockito.when(cmd1.execute()).thenReturn(false);
        Map<String, ICommand> options = new LinkedHashMap<>();
        options.put("1", cmd1);
        Scanner scanner = new Scanner("1\n");
        SubMenu submenu = new SubMenu("Header", scanner);
        options.forEach(submenu::with);
        boolean result = submenu.showAndHandle();
        assertFalse(result); // Should return false if command returns false
        Mockito.verify(cmd1).execute();
    }
}
