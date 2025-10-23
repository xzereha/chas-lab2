package com.example;

import com.example.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Helper class for displaying and handling submenus with dynamic commands.
 */
public class SubMenu {
    private final String header;
    private final Map<String, Command> options;
    private final Scanner scanner;

    /**
     * @param header  The header/title for the submenu.
     * @param options Map of selection code to Command.
     * @param scanner Scanner for user input.
     */
    public SubMenu(String header, Scanner scanner) {
        this.header = header;
        this.options = new HashMap<>();
        this.scanner = scanner;
    }

    public SubMenu with(String code, Command command) {
        options.put(code, command);
        return this;
    }

    /**
     * Displays the submenu and executes the selected command.
     * Returns false if the exit command is chosen.
     */
    public boolean showAndHandle() {
        System.out.println("\n" + header);
        for (Map.Entry<String, Command> entry : options.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().menuText());
        }
        System.out.print("Välj ett alternativ: ");
        String choice = scanner.nextLine();
        Command cmd = options.get(choice);
        if (cmd != null) {
            return cmd.execute();
        } else {
            System.out.println("Ogiltigt val, försök igen.");
            return true;
        }
    }
}
