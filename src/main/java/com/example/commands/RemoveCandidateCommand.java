package com.example.commands;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.CandidateStorage;

/**
 * Command to remove a candidate by name.
 */
public class RemoveCandidateCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(RemoveCandidateCommand.class);
    private final CandidateStorage storage;
    private final Scanner scanner;

    /**
     * Constructs a RemoveCandidateCommand.
     * 
     * @param storage CandidateStorage to remove candidate from. Must not be null.
     * @param scanner Scanner for user input. Must not be null.
     * @throws NullPointerException if storage or scanner is null
     */
    public RemoveCandidateCommand(CandidateStorage storage, Scanner scanner) {
        if (storage == null)
            throw new NullPointerException("CandidateStorage must not be null");
        if (scanner == null)
            throw new NullPointerException("Scanner must not be null");
        this.storage = storage;
        this.scanner = scanner;
    }

    /**
     * Executes the command to remove a candidate by name.
     * Prompts for name, reprompts if blank. Returns false if input stream ends.
     * 
     * @return true if menu should continue, false if input stream ends
     * @throws RuntimeException if an unexpected error occurs during removal
     */
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

    /**
     * Returns the menu text for this command.
     * 
     * @return menu text string
     */
    @Override
    public String menuText() {
        return "Ta bort kandidat";
    }
}
