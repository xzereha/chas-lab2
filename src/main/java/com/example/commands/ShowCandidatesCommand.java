package com.example.commands;

import com.example.Menu;

public class ShowCandidatesCommand implements Command {
    private final Menu menu;

    public ShowCandidatesCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean execute() {
        menu.showCandidatesFlow();
        return true;
    }

    @Override
    public String menuText() {
        return "3. Visa kandidater";
    }
}
