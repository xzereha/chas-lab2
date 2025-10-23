package com.example.commands;

import com.example.Menu;

public class FilterCandidatesCommand implements Command {
    private final Menu menu;

    public FilterCandidatesCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean execute() {
        menu.filterCandidatesFlow();
        return true;
    }

    @Override
    public String menuText() {
        return "4. Filtrera kandidater";
    }
}
