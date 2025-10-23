package com.example;

import org.slf4j.Logger;
import java.util.Map;
import java.util.LinkedHashMap;
import com.example.commands.Command;
import com.example.commands.AddCandidateCommand;
import com.example.commands.RemoveCandidateCommand;
import com.example.commands.ShowCandidatesCommand;
import com.example.commands.FilterCandidatesCommand;
import com.example.commands.ExitCommand;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class Menu {
    private static final Logger logger = LoggerFactory.getLogger(Menu.class);
    private final CandidateStorage storage;
    private final Scanner scanner;
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public Menu(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
        registerCommands();
    }

    private void registerCommands() {
        commands.put("1", new AddCandidateCommand(this));
        commands.put("2", new RemoveCandidateCommand(this));
        commands.put("3", new ShowCandidatesCommand(this));
        commands.put("4", new FilterCandidatesCommand(this));
        commands.put("5", new ExitCommand());
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nMeny:");
            for (Map.Entry<String, Command> entry : commands.entrySet()) {
                System.out.println(entry.getValue().menuText());
            }
            System.out.print("Välj ett alternativ: ");
            String choice = safeNextLine();
            if (choice == null)
                break;
            Command cmd = commands.get(choice);
            if (cmd != null) {
                running = cmd.execute();
            } else {
                System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    public void removeCandidateFlow() {
        System.out.print("Ange namn på kandidat att ta bort: ");
        String name = safeNextLine();
        if (name == null || name.trim().isEmpty()) {
            logger.warn("Ogiltigt namn för borttagning via meny: '{}'.", name);
            return;
        }
        boolean removed = storage.removeCandidate(name);
        if (removed) {
            logger.info("Kandidat borttagen via meny: {}", name);
            System.out.println("Kandidat borttagen.");
        } else {
            logger.warn("Kandidat hittades inte via meny: {}", name);
            System.out.println("Kandidat hittades inte.");
        }
    }

    public void addCandidateFlow() {
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
            storage.addCandidate(candidate);
            logger.info("Kandidat tillagd via meny: {}", candidate);
            System.out.println("Kandidat tillagd!");
        } catch (IllegalArgumentException e) {
            System.out.println("Felaktiga kandidatvärden: " + e.getMessage());
        }
    }

    public void showCandidatesFlow() {
        var candidates = storage.getAllCandidates();
        logger.info("Visar {} kandidater", candidates.size());
        if (candidates.isEmpty()) {
            System.out.println("Inga kandidater finns.");
        } else {
            candidates.forEach(System.out::println);
        }
    }

    public void filterCandidatesFlow() {
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
        java.util.List<Candidate> filtered = filter.filter(storage.getAllCandidates());
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