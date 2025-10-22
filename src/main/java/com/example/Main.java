package com.example;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        CandidateRepository repository = new CandidateRepository();
        Scanner scanner = new Scanner(System.in);
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
                case "1" -> addCandidateFlow(scanner, repository);
                case "2" -> removeCandidateFlow(scanner, repository);
                case "3" -> showCandidatesFlow(repository);
                case "4" -> filterCandidatesFlow(scanner, repository);
                case "5" -> running = false;
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
        scanner.close();
    }

    private static void addCandidateFlow(Scanner scanner, CandidateRepository repository) {
        System.out.print("Namn: ");
        String name = scanner.nextLine();
        System.out.print("Ålder: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Bransch: ");
        String industry = scanner.nextLine();
        System.out.print("Antal år erfarenhet: ");
        int years = Integer.parseInt(scanner.nextLine());
        Candidate candidate = new Candidate(name, age, industry, years);
        repository.addCandidate(candidate);
        logger.info("Kandidat tillagd via meny: {}", candidate);
        System.out.println("Kandidat tillagd!");
    }

    private static void removeCandidateFlow(Scanner scanner, CandidateRepository repository) {
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

    private static void showCandidatesFlow(CandidateRepository repository) {
        var candidates = repository.getAllCandidates();
        logger.info("Visar {} kandidater", candidates.size());
        if (candidates.isEmpty()) {
            System.out.println("Inga kandidater finns.");
        } else {
            candidates.forEach(System.out::println);
        }
    }

    private static void filterCandidatesFlow(Scanner scanner, CandidateRepository repository) {
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
                int years = Integer.parseInt(scanner.nextLine());
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
}