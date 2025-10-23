package com.example.commands;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.CandidateStorage;

public class ShowCandidatesCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(ShowCandidatesCommand.class);
    private final CandidateStorage storage;
    private final Scanner scanner;

    public ShowCandidatesCommand(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        var candidates = storage.getAllCandidates();
        logger.info("Visar {} kandidater", candidates.size());
        if (candidates.isEmpty()) {
            System.out.println("Inga kandidater finns.");
        } else {
            candidates.forEach(System.out::println);
        }
        return true;
    }

    @Override
    public String menuText() {
        return "Visa kandidater";
    }
}
