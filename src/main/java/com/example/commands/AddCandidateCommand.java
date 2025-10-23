package com.example.commands;

import java.util.Optional;
import java.util.Scanner;

import com.example.Candidate;
import com.example.CandidateStorage;

public class AddCandidateCommand implements Command {
    private final CandidateStorage storage;
    private final Scanner scanner;

    public AddCandidateCommand(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
    }

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

    @Override
    public String menuText() {
        return "Lägg till kandidat";
    }

    /**
     * Reads next line from scanner, returns null if no line is available.
     */
    private String safeNextLine() {
        try {
            return scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Reads next line from scanner, returns null if no line is available.
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
