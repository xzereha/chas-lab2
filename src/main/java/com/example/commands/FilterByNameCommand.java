package com.example.commands;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.example.CandidateStorage;
import com.example.NameFilter;

/**
 * Command to filter candidates by name.
 */
public class FilterByNameCommand implements Command {
    private final CandidateStorage storage;
    private final Scanner scanner;

    /**
     * Constructs a FilterByNameCommand.
     * 
     * @param storage CandidateStorage to retrieve candidates from. Must not be
     *                null.
     * @param scanner Scanner for user input. Must not be null.
     * @throws NullPointerException if storage or scanner is null
     */
    public FilterByNameCommand(CandidateStorage storage, Scanner scanner) {
        if (storage == null)
            throw new NullPointerException("CandidateStorage must not be null");
        if (scanner == null)
            throw new NullPointerException("Scanner must not be null");
        this.storage = storage;
        this.scanner = scanner;
    }

    /**
     * Executes the command to filter candidates by name. Prompts for name,
     * reprompts if blank. Returns false if input stream ends.
     * 
     * @return true if menu should continue, false if input stream ends
     * @throws IllegalArgumentException if name is null or empty
     * @throws RuntimeException         if an unexpected error occurs during
     *                                  filtering
     */
    @Override
    public boolean execute() {
        System.out.print("Ange namn att filtrera på: ");
        while (true) {
            try {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Namnet får inte vara tomt. Försök igen.");
                    continue;
                }
                var filter = new NameFilter(name);
                var candidates = filter.filter(storage.getAllCandidates());
                if (candidates.isEmpty()) {
                    System.out.println("Inga kandidater matchade kriterierna.");
                } else {
                    System.out.println("Matchande kandidater:");
                    candidates.forEach(System.out::println);
                }
                return true;
            } catch (NoSuchElementException e) {
                System.out.println("Inmatning avbröts.");
                return false;
            }
        }
    }

    /**
     * Returns the menu text for this command.
     * 
     * @return menu text string
     */
    @Override
    public String menuText() {
        return "Filtrera på namn";
    }
}
