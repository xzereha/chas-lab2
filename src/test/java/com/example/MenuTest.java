package com.example;

import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void addCandidateFlow_invalidName_thenValid_shouldAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        // Try blank name, then valid name
        String input = "1\n \nAnna\n30\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
        Candidate c = repo.getAllCandidates().get(0);
        assertEquals("Anna", c.getName());
    }

    @Test
    void addCandidateFlow_invalidAge_thenValid_shouldAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        // Try invalid age, then valid age
        String input = "1\nAnna\n-1\n30\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
        Candidate c = repo.getAllCandidates().get(0);
        assertEquals(30, c.getAge());
    }

    @Test
    void addCandidateFlow_invalidIndustry_thenValid_shouldAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        // Try blank industry, then valid industry
        String input = "1\nAnna\n30\n \nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
        Candidate c = repo.getAllCandidates().get(0);
        assertEquals("IT", c.getIndustry());
    }

    @Test
    void addCandidateFlow_invalidExperience_thenValid_shouldAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        // Try invalid experience, then valid experience
        String input = "1\nAnna\n30\nIT\n-1\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
        Candidate c = repo.getAllCandidates().get(0);
        assertEquals(5, c.getYearsOfExperience());
    }

    @Test
    void addCandidateFlow_invalidName_shouldNotProceed() {
        CandidateRepository repo = new CandidateRepository();
        // Try to add candidate with blank name, then valid age/industry/experience
        String input = "1\n \n30\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_invalidAge_shouldNotProceed() {
        CandidateRepository repo = new CandidateRepository();
        // Try to add candidate with valid name, then invalid age, then valid
        // industry/experience
        String input = "1\nAnna\n-1\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_invalidIndustry_shouldNotProceed() {
        CandidateRepository repo = new CandidateRepository();
        // Try to add candidate with valid name/age, then blank industry, then input
        // ends (simulates user exit)
        String input = "1\nAnna\n30\n \n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_invalidExperience_shouldNotProceed() {
        CandidateRepository repo = new CandidateRepository();
        // Try to add candidate with valid name/age/industry, then invalid experience,
        // then input ends (simulates user exit)
        String input = "1\nAnna\n30\nIT\n-1\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void mainMenu_nullInput_shouldExitGracefully() {
        CandidateRepository repo = new CandidateRepository();
        Scanner scanner = new Scanner(""); // no input at all
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void mainMenu_defaultCase_shouldHandleGracefully() {
        CandidateRepository repo = new CandidateRepository();
        Scanner scanner = new Scanner("invalid\n5\n"); // invalid menu choice, then exit
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void showCandidatesFlow_emptyList_shouldDisplayNoCandidates() {
        CandidateRepository repo = new CandidateRepository();
        String input = "3\n5\n"; // show candidates, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void addCandidateFlow_blankName_shouldNotAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        String input = "1\n \n30\nIT\n5\n5\n"; // blank name
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_negativeAge_shouldNotAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        String input = "1\nAnna\n-1\nIT\n5\n5\n"; // negative age
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_blankIndustry_shouldNotAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        // blank industry, then valid industry
        String input = "1\nAnna\n30\n \nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
        Candidate c = repo.getAllCandidates().get(0);
        assertEquals("Anna", c.getName());
        assertEquals("IT", c.getIndustry());
    }

    @Test
    void addCandidateFlow_negativeExperience_shouldNotAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        // negative experience, then valid experience
        String input = "1\nAnna\n30\nIT\n-1\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
        Candidate c = repo.getAllCandidates().get(0);
        assertEquals("Anna", c.getName());
        assertEquals(5, c.getYearsOfExperience());
    }

    @Test
    void removeCandidateFlow_blankName_shouldNotRemoveCandidate() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "2\n \n5\n"; // blank name
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
    }

    @Test
    void removeCandidateFlow_nullName_shouldNotRemoveCandidate() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "2\n\n5\n"; // null/empty name
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
    }

    @Test
    void filterCandidatesFlow_blankIndustry_shouldHandleGracefully() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "4\n1\n \n5\n"; // blank industry
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_negativeExperience_shouldHandleGracefully() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "4\n2\n-1\n5\n"; // negative experience
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void addCandidateFlow_invalidInput_shouldHandleGracefully() {
        CandidateRepository repo = new CandidateRepository();
        Scanner scanner = new Scanner("\n\n\n\n\n"); // blank name, age, industry, years
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_validInput_shouldAddCandidate() {
        CandidateRepository repo = new CandidateRepository();
        String input = "1\nAnna\n30\nIT\n5\n5\n"; // add candidate, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().size());
        Candidate c = repo.getAllCandidates().get(0);
        assertEquals("Anna", c.getName());
        assertEquals(30, c.getAge());
        assertEquals("IT", c.getIndustry());
        assertEquals(5, c.getYearsOfExperience());
    }

    @Test
    void removeCandidateFlow_validInput_shouldRemoveCandidate() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "2\nAnna\n5\n"; // remove candidate, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void showCandidatesFlow_shouldDisplayCandidates() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "3\n5\n"; // show candidates, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_validIndustry_shouldFilter() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        repo.addCandidate(new Candidate("Bo", 40, "Ekonomi", 10));
        String input = "4\n1\nIT\n5\n"; // filter by industry IT, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_validExperience_shouldFilter() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        repo.addCandidate(new Candidate("Bo", 40, "Ekonomi", 10));
        String input = "4\n2\n10\n5\n"; // filter by experience >=10, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_validName_shouldFilter() {
        CandidateRepository repo = new CandidateRepository();
        repo.addCandidate(new Candidate("Bo", 40, "Ekonomi", 10));
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "4\n3\nAnna\n5\n"; // filter by name 'Anna', then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(1, repo.getAllCandidates().stream().filter(c -> c.getName().equals("Anna")).count());
    }

    @Test
    void filterCandidatesFlow_invalidChoice_shouldHandleGracefully() {
        CandidateRepository repo = new CandidateRepository();
        Scanner scanner = new Scanner("4\n9\n5\n"); // invalid filter choice, then exit
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }
}
