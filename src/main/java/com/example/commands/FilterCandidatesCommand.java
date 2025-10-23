package com.example.commands;

import java.util.Scanner;

import com.example.CandidateStorage;
import com.example.SubMenu;

public class FilterCandidatesCommand implements Command {
    private final SubMenu subMenu;

    public FilterCandidatesCommand(CandidateStorage storage, Scanner scanner) {
        this.subMenu = new SubMenu("Filtrera kandidater", scanner)
                .with("1", new FilterByNameCommand(storage, scanner))
                .with("2", new FilterByExperienceCommand(storage, scanner))
                .with("3", new FilterByIndustryCommand(storage, scanner));
    }

    @Override
    public boolean execute() {
        subMenu.showAndHandle();
        return true;
    }

    @Override
    public String menuText() {
        return "Filtrera kandidater";
    }
}
