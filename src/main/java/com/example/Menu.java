package com.example;

import org.slf4j.Logger;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class Menu {
    private static final Logger logger = LoggerFactory.getLogger(Menu.class);
    private final CandidateRepository repository;
    private final Scanner scanner;

    public Menu(CandidateRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nMeny:");
            System.out.println("1. Lägg till kandidat");
            System.out.println("2. Ta bort kandidat");
            System.out.println("3. Visa kandidater");
            System.out.println("4. Filtrera kandidater");
            System.out.println("5. Avsluta");
            System.out.print("Välj ett alternativ: ");
            String choice = safeNextLine();
            if (choice == null)
                break;
            switch (choice) {
                case "1" -> addCandidateFlow();
                case "2" -> removeCandidateFlow();
                case "3" -> showCandidatesFlow();
                case "4" -> filterCandidatesFlow();
                case "5" -> running = false;
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private void removeCandidateFlow() {
        System.out.print("Ange namn på kandidat att ta bort: ");
        String name = safeNextLine();
        if (name == null || name.trim().isEmpty()) {
            logger.warn("Ogiltigt namn för borttagning via meny: '{}'.", name);
            return;
        }
        boolean removed = repository.removeCandidate(name);
        if (removed) {
            logger.info("Kandidat borttagen via meny: {}", name);
            System.out.println("Kandidat borttagen.");
        } else {
            logger.warn("Kandidat hittades inte via meny: {}", name);
            System.out.println("Kandidat hittades inte.");
        }
    }

    private void addCandidateFlow() {
        Optional<String> nameOpt = promptForName();
        if (nameOpt.isEmpty())
            return;
        Optional<Integer> ageOpt = promptForAge();
        if (ageOpt.isEmpty())
            return;
        Optional<String> industryOpt = promptForIndustry();
        if (industryOpt.isEmpty())
            return;
        Optional<Integer> yearsOpt = promptForExperience();
        if (yearsOpt.isEmpty())
            return;
        String name = nameOpt.get();
        int age = ageOpt.get();
        String industry = industryOpt.get();
        int years = yearsOpt.get();
        try {
            Candidate candidate = new Candidate(name, age, industry, years);
            repository.addCandidate(candidate);
            logger.info("Kandidat tillagd via meny: {}", candidate);
            System.out.println("Kandidat tillagd!");
        } catch (IllegalArgumentException e) {
            System.out.println("Felaktiga kandidatvärden: " + e.getMessage());
        }
    }

    private void showCandidatesFlow() {
        var candidates = repository.getAllCandidates();
        logger.info("Visar {} kandidater", candidates.size());
        if (candidates.isEmpty()) {
            System.out.println("Inga kandidater finns.");
        } else {
            candidates.forEach(System.out::println);
        }
    }

    private void filterCandidatesFlow() {
        System.out.println("Filtrera på: 1. Bransch 2. Erfarenhet 3. Namn");
        String filterChoice = safeNextLine();
        if (filterChoice == null)
            return;
        CandidateFilter filter = null;
        switch (filterChoice) {
            case "1": {
                System.out.print("Ange bransch: ");
                String industry = safeNextLine();
                if (industry == null)
                    return;
                filter = new IndustryFilter(industry);
                break;
            }
            case "2": {
                System.out.print("Minsta antal år erfarenhet: ");
                String yearsStr = safeNextLine();
                if (yearsStr == null)
                    return;
                int years = parseIntInput(yearsStr);
                filter = new ExperienceFilter(years);
                break;
            }
            case "3": {
                System.out.print("Ange namn: ");
                String name = safeNextLine();
                if (name == null)
                    return;
                filter = new NameFilter(name);
                break;
            }
            default: {
                System.out.println("Ogiltigt val.");
                return;
            }
        }
        if (filter == null)
            return;
        java.util.List<Candidate> filtered = filter.filter(repository.getAllCandidates());
        logger.info("Filtrering utförd med {} kandidater", filtered.size());
        if (filtered.isEmpty()) {
            System.out.println("Inga kandidater matchar filtret.");
        } else {
            filtered.forEach(System.out::println);
        }
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
     * Parses an integer from input string, returns 0 if invalid.
     */
    private int parseIntInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            logger.warn("Ogiltigt heltal: {}", input);
            System.out.println("Ogiltigt heltal, försök igen.");
            return 0;
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