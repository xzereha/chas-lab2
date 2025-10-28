package com.example.commands;

public interface ICommand {
    /**
     * Executes the command. Returns false to exit the menu loop.
     */
    boolean execute();

    /**
     * Returns the menu text for this command.
     */
    String menuText();
}
