package com.example.commands;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.example.CandidateStorage;
import com.example.NameFilter;

public class FilterByNameCommand implements Command {
    private final CandidateStorage storage;
    private final Scanner scanner;

    public FilterByNameCommand(CandidateStorage storage, Scanner scanner) {
        this.storage = storage;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.print("Ange namn att filtrera på: ");
        while (true) {
            try {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Namnet får inte vara tomt. Försök igen.");
                    continue;
                }
                var filter = new NameFilter(name);
                var candidates = filter.filter(storage.getAllCandidates());
                if (candidates.isEmpty()) {
                    System.out.println("Inga kandidater matchade kriterierna.");
                } else {
                    System.out.println("Matchande kandidater:");
                    candidates.forEach(System.out::println);
                }
                return true;
            } catch (NoSuchElementException e) {
                System.out.println("Inmatning avbröts.");
                return false;
            }
        }
    }

    @Override
    public String menuText() {
        return "Filtrera på namn";
    }
}
