package com.example;

import java.util.Scanner;

import com.example.menu.Menu;

/**
 * Application entrypoint. Delegates menu logic to Menu class.
 */
public class Main {
    public static void main(String[] args) {
        CandidateRepository repository = new CandidateRepository();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(repository, scanner);
        menu.run();
    }
}