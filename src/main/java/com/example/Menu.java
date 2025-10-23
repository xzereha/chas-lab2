package com.example;

import com.example.commands.AddCandidateCommand;
import com.example.commands.RemoveCandidateCommand;
import com.example.commands.ShowCandidatesCommand;
import com.example.commands.FilterCandidatesCommand;
import com.example.commands.ExitCommand;
import java.util.Scanner;

public class Menu {
    private final CandidateStorage storage;
    private final Scanner scanner;
    private final SubMenu subMenu;

    public Menu(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
        this.subMenu = new SubMenu("Meny", scanner);
        registerCommands();
    }

    private void registerCommands() {
        subMenu.with("1", new AddCandidateCommand(this.storage, scanner))
                .with("2", new RemoveCandidateCommand(this.storage, scanner))
                .with("3", new ShowCandidatesCommand(this.storage, scanner))
                .with("4", new FilterCandidatesCommand(this.storage, scanner))
                .with("5", new ExitCommand());
    }

    public void run() {
        while (subMenu.showAndHandle()) {
            // Actual logic is done in the showAndHandle method
        }
    }
}