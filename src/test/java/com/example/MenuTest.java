package com.example;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void addCandidateFlow_invalidName_thenValid_shouldAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "1\n \nAnna\n30\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo).addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") && c.getAge() == 30
                && c.getIndustry().equals("IT") && c.getYearsOfExperience() == 5));
    }

    @Test
    void addCandidateFlow_invalidAge_thenValid_shouldAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "1\nAnna\n-1\n30\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo).addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") && c.getAge() == 30
                && c.getIndustry().equals("IT") && c.getYearsOfExperience() == 5));
    }

    @Test
    void addCandidateFlow_invalidIndustry_thenValid_shouldAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        // Try blank industry, then valid industry
        String input = "1\nAnna\n30\n \nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo, Mockito.times(1)).addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") &&
                c.getAge() == 30 && c.getIndustry().equals("IT") && c.getYearsOfExperience() == 5));
    }

    @Test
    void addCandidateFlow_invalidExperience_thenValid_shouldAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        // Try invalid experience, then valid experience
        String input = "1\nAnna\n30\nIT\n-1\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo, Mockito.times(1)).addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") &&
                c.getAge() == 30 && c.getIndustry().equals("IT") && c.getYearsOfExperience() == 5));
    }

    @Test
    void addCandidateFlow_invalidName_shouldNotProceed() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        // Try to add candidate with blank name, then valid age/industry/experience
        String input = "1\n \n30\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_invalidAge_shouldNotProceed() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
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
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
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
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
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
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner(""); // no input at all
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void mainMenu_defaultCase_shouldHandleGracefully() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("invalid\n5\n"); // invalid menu choice, then exit
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void showCandidatesFlow_emptyList_shouldDisplayNoCandidates() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "3\n5\n"; // show candidates, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void addCandidateFlow_blankName_shouldNotAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "1\n \n30\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo, Mockito.never()).addCandidate(Mockito.any());
    }

    @Test
    void addCandidateFlow_negativeAge_shouldNotAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "1\nAnna\n-1\nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo, Mockito.never()).addCandidate(Mockito.any());
    }

    @Test
    void addCandidateFlow_blankIndustry_shouldNotAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "1\nAnna\n30\n \nIT\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo)
                .addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") && c.getIndustry().equals("IT")));
    }

    @Test
    void addCandidateFlow_negativeExperience_shouldNotAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "1\nAnna\n30\nIT\n-1\n5\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo)
                .addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") && c.getYearsOfExperience() == 5));
    }

    @Test
    void removeCandidateFlow_blankName_shouldNotRemoveCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "2\n \n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo, Mockito.never()).removeCandidate(Mockito.any());
    }

    @Test
    void removeCandidateFlow_nullName_shouldNotRemoveCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "2\n\n5\n";
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo, Mockito.never()).removeCandidate(Mockito.any());
    }

    @Test
    void filterCandidatesFlow_blankIndustry_shouldHandleGracefully() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "4\n1\n \n5\n"; // blank industry
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_negativeExperience_shouldHandleGracefully() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "4\n2\n-1\n5\n"; // negative experience
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void addCandidateFlow_invalidInput_shouldHandleGracefully() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("\n\n\n\n\n"); // blank name, age, industry, years
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void addCandidateFlow_validInput_shouldAddCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        String input = "1\nAnna\n30\nIT\n5\n5\n"; // add candidate, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        Mockito.verify(repo, Mockito.times(1)).addCandidate(Mockito.argThat(c -> c.getName().equals("Anna") &&
                c.getAge() == 30 &&
                c.getIndustry().equals("IT") &&
                c.getYearsOfExperience() == 5));
    }

    @Test
    void removeCandidateFlow_validInput_shouldRemoveCandidate() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "2\nAnna\n5\n"; // remove candidate, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        menu.run();
        assertEquals(0, repo.getAllCandidates().size());
    }

    @Test
    void showCandidatesFlow_shouldDisplayCandidates() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        String input = "3\n5\n"; // show candidates, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_validIndustry_shouldFilter() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        repo.addCandidate(new Candidate("Bo", 40, "Ekonomi", 10));
        String input = "4\n1\nIT\n5\n"; // filter by industry IT, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_validExperience_shouldFilter() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        repo.addCandidate(new Candidate("Anna", 30, "IT", 5));
        repo.addCandidate(new Candidate("Bo", 40, "Ekonomi", 10));
        String input = "4\n2\n10\n5\n"; // filter by experience >=10, then exit
        Scanner scanner = new Scanner(input);
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }

    @Test
    void filterCandidatesFlow_invalidChoice_shouldHandleGracefully() {
        CandidateStorage repo = Mockito.mock(CandidateStorage.class);
        Scanner scanner = new Scanner("4\n9\n5\n"); // invalid filter choice, then exit
        Menu menu = new Menu(repo, scanner);
        assertDoesNotThrow(menu::run);
    }
}
