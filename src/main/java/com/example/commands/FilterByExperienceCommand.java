package com.example.commands;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.example.CandidateStorage;
import com.example.ExperienceFilter;

public class FilterByExperienceCommand implements Command {
    private final CandidateStorage storage;
    private final Scanner scanner;

    public FilterByExperienceCommand(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
    }

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

    @Override
    public String menuText() {
        return "Filtrera på erfarenhet";
    }
}
