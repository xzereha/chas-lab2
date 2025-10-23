package com.example.commands;

import com.example.Menu;

public class AddCandidateCommand implements Command {
    private final Menu menu;

    public AddCandidateCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean execute() {
        menu.addCandidateFlow();
        return true;
    }

    @Override
    public String menuText() {
        return "1. Lägg till kandidat";
    }
}
