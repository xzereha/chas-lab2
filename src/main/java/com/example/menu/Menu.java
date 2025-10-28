package com.example.menu;

import com.example.ICandidateStorage;
import com.example.commands.AddCandidateCommand;
import com.example.commands.RemoveCandidateCommand;
import com.example.commands.ShowCandidatesCommand;
import com.example.commands.FilterCandidatesCommand;
import com.example.commands.ExitCommand;
import java.util.Scanner;

public class Menu {
    private final ICandidateStorage storage;
    private final Scanner scanner;
    private final SubMenu subMenu;

    /**
     * Constructs a Menu.
     * 
     * @param storage The candidate storage.
     * @param scanner The scanner for user input.
     */
    public Menu(ICandidateStorage storage, Scanner scanner) {
        if (storage == null)
            throw new IllegalArgumentException("CandidateStorage must not be null");
        if (scanner == null)
            throw new IllegalArgumentException("Scanner must not be null");
        this.storage = storage;
        this.scanner = scanner;
        this.subMenu = new SubMenu("Meny", scanner);
        registerCommands();
    }

    /**
     * Registers all commands to the submenu.
     */
    private void registerCommands() {
        subMenu.with("1", new AddCandidateCommand(this.storage, scanner))
                .with("2", new RemoveCandidateCommand(this.storage, scanner))
                .with("3", new ShowCandidatesCommand(this.storage, scanner))
                .with("4", new FilterCandidatesCommand(this.storage, scanner))
                .with("5", new ExitCommand());
    }

    /**
     * Runs the menu loop until exit.
     */
    public void run() {
        boolean running = true;
        while (running) {
            running = subMenu.showAndHandle();
        }
    }
}