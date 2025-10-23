package com.example.commands;

public class ExitCommand implements Command {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public String menuText() {
        return "5. Avsluta";
    }
}
