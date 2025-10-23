package com.example.commands;

import java.util.Optional;
import java.util.Scanner;

import com.example.Candidate;
import com.example.CandidateStorage;

/**
 * Command to add a candidate.
 */
public class AddCandidateCommand implements Command {
    private final CandidateStorage storage;
    private final Scanner scanner;

    /**
     * Constructs an AddCandidateCommand.
     * 
     * @param storage CandidateStorage to add candidate to. Must not be null.
     * @param scanner Scanner for user input. Must not be null.
     * @throws NullPointerException if storage or scanner is null
     */
    public AddCandidateCommand(CandidateStorage storage, Scanner scanner) {
        if (storage == null)
            throw new NullPointerException("CandidateStorage must not be null");
        if (scanner == null)
            throw new NullPointerException("Scanner must not be null");
        this.storage = storage;
        this.scanner = scanner;
    }

    /**
     * Executes the command to add a candidate. Prompts for all candidate fields,
     * reprompts if invalid. Returns false if input stream ends.
     * 
     * @return true if menu should continue, false if input stream ends
     * @throws IllegalArgumentException if candidate fields are invalid
     * @throws RuntimeException         if an unexpected error occurs during
     *                                  addition
     */
    @Override
    public boolean execute() {
        Optional<String> nameOpt = promptForName();
        if (nameOpt.isEmpty())
            return false;
        Optional<Integer> ageOpt = promptForAge();
        if (ageOpt.isEmpty())
            return false;
        Optional<String> industryOpt = promptForIndustry();
        if (industryOpt.isEmpty())
            return false;
        Optional<Integer> yearsOpt = promptForExperience();
        if (yearsOpt.isEmpty())
            return false;

        Candidate candidate = new Candidate(nameOpt.get(), ageOpt.get(), industryOpt.get(), yearsOpt.get());
        storage.addCandidate(candidate);
        return true;
    }

    /**
     * Returns the menu text for this command.
     * 
     * @return menu text string
     */
    @Override
    public String menuText() {
        return "Lägg till kandidat";
    }

    /**
     * Reads next line from scanner, returns null if no line is available.
     * 
     * @return next line as String, or null if input stream ends
     */
    private String safeNextLine() {
        try {
            return scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Prompts for candidate name, reprompts if blank. Returns empty if input stream
     * ends.
     * 
     * @return Optional containing name, or empty if input stream ends
     */
    private Optional<String> promptForName() {
        while (true) {
            System.out.print("Namn: ");
            String name = safeNextLine();
            if (name == null)
                return Optional.empty();
            if (name.trim().isEmpty()) {
                System.out.println("Namn får inte vara tomt. Försök igen.");
                continue;
            }
            return Optional.of(name.trim());
        }
    }

    /**
     * Prompts for candidate age, reprompts if invalid. Returns empty if input
     * stream
     * ends.
     * 
     * @return Optional containing age, or empty if input stream ends
     */
    private Optional<Integer> promptForAge() {
        while (true) {
            System.out.print("Ålder: ");
            String ageStr = safeNextLine();
            if (ageStr == null)
                return Optional.empty();
            try {
                int age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    System.out.println("Ålder måste vara ett positivt heltal. Försök igen.");
                    continue;
                }
                return Optional.of(age);
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt heltal, försök igen.");
            }
        }
    }

    /**
     * Prompts for candidate industry, reprompts if blank. Returns empty if input
     * stream ends.
     * 
     * @return Optional containing industry, or empty if input stream ends
     */
    private Optional<String> promptForIndustry() {
        while (true) {
            System.out.print("Bransch: ");
            String industry = safeNextLine();
            if (industry == null)
                return Optional.empty();
            if (industry.trim().isEmpty()) {
                System.out.println("Bransch får inte vara tomt. Försök igen.");
                continue;
            }
            return Optional.of(industry.trim());
        }
    }

    /**
     * Prompts for candidate years of experience, reprompts if invalid. Returns
     * empty
     * if input stream ends.
     * 
     * @return Optional containing years, or empty if input stream ends
     */
    private Optional<Integer> promptForExperience() {
        while (true) {
            System.out.print("Antal år erfarenhet: ");
            String yearsStr = safeNextLine();
            if (yearsStr == null)
                return Optional.empty();
            try {
                int years = Integer.parseInt(yearsStr);
                if (years < 0) {
                    System.out.println("Erfarenhet kan inte vara negativ. Försök igen.");
                    continue;
                }
                return Optional.of(years);
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt heltal, försök igen.");
            }
        }
    }
}
