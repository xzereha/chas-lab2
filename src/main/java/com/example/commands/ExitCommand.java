package com.example.commands;

/**
 * Command to exit the menu loop.
 */
public class ExitCommand implements ICommand {
    /**
     * Executes the exit command.
     * 
     * @return false (signals menu loop to exit)
     */
    @Override
    public boolean execute() {
        return false;
    }

    /**
     * Returns the menu text for this command.
     * 
     * @return menu text string
     */
    @Override
    public String menuText() {
        return "Avsluta";
    }
}
