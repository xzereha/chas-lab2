package com.example;

import org.slf4j.Logger;
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
            String choice = scanner.nextLine();
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

    private void addCandidateFlow() {
        System.out.print("Namn: ");
        String name = scanner.nextLine();
        System.out.print("Ålder: ");
        int age = parseIntInput(scanner.nextLine());
        System.out.print("Bransch: ");
        String industry = scanner.nextLine();
        System.out.print("Antal år erfarenhet: ");
        int years = parseIntInput(scanner.nextLine());
        Candidate candidate = new Candidate(name, age, industry, years);
        repository.addCandidate(candidate);
        logger.info("Kandidat tillagd via meny: {}", candidate);
        System.out.println("Kandidat tillagd!");
    }

    private void removeCandidateFlow() {
        System.out.print("Ange namn på kandidat att ta bort: ");
        String name = scanner.nextLine();
        boolean removed = repository.removeCandidate(name);
        if (removed) {
            logger.info("Kandidat borttagen via meny: {}", name);
            System.out.println("Kandidat borttagen.");
        } else {
            logger.warn("Kandidat hittades inte via meny: {}", name);
            System.out.println("Kandidat hittades inte.");
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
        System.out.println("Filtrera på: 1. Bransch 2. Erfarenhet 3. Namn (A-Ö)");
        String filterChoice = scanner.nextLine();
        CandidateFilter filter = null;
        switch (filterChoice) {
            case "1" -> {
                System.out.print("Ange bransch: ");
                String industry = scanner.nextLine();
                filter = new IndustryFilter(industry);
            }
            case "2" -> {
                System.out.print("Minsta antal år erfarenhet: ");
                int years = parseIntInput(scanner.nextLine());
                filter = new ExperienceFilter(years);
            }
            case "3" -> filter = new AlphabeticalNameSorter();
            default -> {
                System.out.println("Ogiltigt val.");
                return;
            }
        }
        var filtered = filter.filter(repository.getAllCandidates());
        logger.info("Filtrering utförd med {} kandidater", filtered.size());
        if (filtered.isEmpty()) {
            System.out.println("Inga kandidater matchar filtret.");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    private int parseIntInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            logger.warn("Ogiltigt heltal: {}", input);
            System.out.println("Ogiltigt heltal, försök igen.");
            return 0;
        }
    }
}
