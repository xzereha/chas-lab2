package com.example.commands;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.example.CandidateStorage;
import com.example.IndustryFilter;

/**
 * Command to filter candidates by industry.
 */
public class FilterByIndustryCommand implements Command {
    private final CandidateStorage storage;
    private final Scanner scanner;

    /**
     * Constructs a FilterByIndustryCommand.
     * 
     * @param storage CandidateStorage to retrieve candidates from. Must not be
     *                null.
     * @param scanner Scanner for user input. Must not be null.
     * @throws NullPointerException if storage or scanner is null
     */
    public FilterByIndustryCommand(CandidateStorage storage, Scanner scanner) {
        if (storage == null)
            throw new NullPointerException("CandidateStorage must not be null");
        if (scanner == null)
            throw new NullPointerException("Scanner must not be null");
        this.storage = storage;
        this.scanner = scanner;
    }

    /**
     * Executes the command to filter candidates by industry. Prompts for industry,
     * reprompts if blank. Returns false if input stream ends.
     * 
     * @return true if menu should continue, false if input stream ends
     * @throws IllegalArgumentException if industry is null or empty
     * @throws RuntimeException         if an unexpected error occurs during
     *                                  filtering
     */
    @Override
    public boolean execute() {
        System.out.print("Ange bransch att filtrera på: ");
        String industry;
        while (true) {
            try {
                industry = scanner.nextLine().trim();
                if (industry.isEmpty()) {
                    System.out.println("Bransch får inte vara tom. Försök igen.");
                    continue;
                } else {
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Inmatning avbröts.");
                return false;
            }
        }
        var filter = new IndustryFilter(industry);
        var candidates = filter.filter(storage.getAllCandidates());
        if (candidates.isEmpty()) {
            System.out.println("Inga kandidater matchade kriterierna.");
        } else {
            System.out.println("Matchande kandidater:");
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
        return "Filtrera på bransch";
    }
}
