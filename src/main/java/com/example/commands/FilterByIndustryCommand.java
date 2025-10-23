package com.example.commands;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.example.CandidateStorage;
import com.example.IndustryFilter;

public class FilterByIndustryCommand implements Command {
    private final CandidateStorage storage;
    private final Scanner scanner;

    public FilterByIndustryCommand(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
    }

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

    @Override
    public String menuText() {
        return "Filtrera på bransch";
    }
}
