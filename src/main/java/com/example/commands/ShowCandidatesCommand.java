package com.example.commands;

import com.example.AlphabeticalNameSorter;
import com.example.ICandidateStorage;
import java.util.Scanner;

/**
 * Command to display all candidates.
 */
public class ShowCandidatesCommand implements ICommand {
    private final ICandidateStorage storage;
    private final Scanner scanner;
    private final AlphabeticalNameSorter filter;

    /**
     * Constructs a ShowCandidatesCommand.
     * 
     * @param storage CandidateStorage to retrieve candidates from. Must not be
     *                null.
     * @param scanner Scanner for user input. Must not be null.
     * @throws NullPointerException if storage or scanner is null
     */
    public ShowCandidatesCommand(ICandidateStorage storage, Scanner scanner) {
        if (storage == null)
            throw new NullPointerException("CandidateStorage must not be null");
        if (scanner == null)
            throw new NullPointerException("Scanner must not be null");
        this.storage = storage;
        this.scanner = scanner;
        this.filter = new AlphabeticalNameSorter();
    }

    /**
     * Executes the command to display all candidates.
     * Prints each candidate to standard output. If no candidates exist, prints a
     * message.
     * 
     * @return true (always continues menu loop)
     * @throws RuntimeException if an unexpected error occurs during candidate
     *                          retrieval
     */
    @Override
    public boolean execute() {
        var candidates = filter.filter(storage.getAllCandidates());
        if (candidates.isEmpty()) {
            System.out.println("Inga kandidater finns.");
        } else {
            candidates.forEach(System.out::println);
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
        return "Visa kandidater";
    }
}
