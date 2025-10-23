package com.example.commands;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.CandidateStorage;

public class RemoveCandidateCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(RemoveCandidateCommand.class);
    private final CandidateStorage storage;
    private final Scanner scanner;

    public RemoveCandidateCommand(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.print("Ange namn på kandidat att ta bort: ");
        String name = null;
        while (true) {
            try {
                name = scanner.nextLine();
                if (name.trim().isEmpty()) {
                    logger.warn("Ogiltigt namn för borttagning via meny: '{}'.", name);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Inmatning avbröts.");
                return false;
            }
        }
        boolean removed = storage.removeCandidate(name);
        if (removed) {
            logger.info("Kandidat borttagen via meny: {}", name);
            System.out.println("Kandidat borttagen.");
        } else {
            logger.warn("Kandidat hittades inte via meny: {}", name);
            System.out.println("Kandidat hittades inte.");
        }
        return true;
    }

    @Override
    public String menuText() {
        return "Ta bort kandidat";
    }
}
