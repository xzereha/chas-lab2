package com.example.commands;

import com.example.Menu;

public class RemoveCandidateCommand implements Command {
    private final Menu menu;

    public RemoveCandidateCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean execute() {
        menu.removeCandidateFlow();
        return true;
    }

    @Override
    public String menuText() {
        return "2. Ta bort kandidat";
    }
}
