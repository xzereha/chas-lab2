package com.example.commands;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.example.ICandidateStorage;
import com.example.filters.ExperienceFilter;

/**
 * Command to filter candidates by years of experience.
 */
public class FilterByExperienceCommand implements ICommand {
    private final ICandidateStorage storage;
    private final Scanner scanner;

    /**
     * Constructs a FilterByExperienceCommand.
     * 
     * @param storage CandidateStorage to retrieve candidates from. Must not be
     *                null.
     * @param scanner Scanner for user input. Must not be null.
     * @throws NullPointerException if storage or scanner is null
     */
    public FilterByExperienceCommand(ICandidateStorage storage, Scanner scanner) {
        if (storage == null)
            throw new NullPointerException("CandidateStorage must not be null");
        if (scanner == null)
            throw new NullPointerException("Scanner must not be null");
        this.storage = storage;
        this.scanner = scanner;
    }

    /**
     * Executes the command to filter candidates by experience. Prompts for years,
     * reprompts if invalid. Returns false if input stream ends.
     * 
     * @return true if menu should continue, false if input stream ends
     * @throws IllegalArgumentException if years is negative
     * @throws RuntimeException         if an unexpected error occurs during
     *                                  filtering
     */
    @Override
    public boolean execute() {
        System.out.println("Minsta erfarenhet i år: ");
        int years = 0;
        while (true) {
            try {
                years = Integer.parseInt(scanner.nextLine());
                if (years < 0) {
                    System.out.println("Antal år måste vara noll eller positivt. Försök igen.");
                    continue;
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt antal år. Försök igen.");
            } catch (NoSuchElementException e) {
                System.out.println("Inmatning avbröts.");
                return false;
            }
        }
        var filter = new ExperienceFilter(years);
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
        return "Filtrera på erfarenhet";
    }
}
