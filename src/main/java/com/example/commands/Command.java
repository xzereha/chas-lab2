package com.example.commands;

public interface Command {
    /**
     * Executes the command.
     * 
     * @return true if the command was successful, false otherwise.
     */
    boolean execute();

    /**
     * Returns the menu text for this command.
     * 
     * @return the menu text
     */
    String menuText();
}
