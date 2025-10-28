package com.example.models;

/**
 * Data model for a candidate in the recruitment system.
 */
public class Candidate {
    private String name;
    private int age;
    private String industry;
    private int yearsOfExperience;

    /**
     * Constructs a Candidate. Throws IllegalArgumentException for null/invalid
     * arguments.
     * 
     * @param name              Name of candidate (not null/blank)
     * @param age               Age (must be >= 0)
     * @param industry          Industry (not null/blank)
     * @param yearsOfExperience Years of experience (must be >= 0)
     */
    public Candidate(String name, int age, String industry, int yearsOfExperience) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Namn får inte vara null eller tomt.");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Ålder måste vara >= 0.");
        }
        if (industry == null || industry.isBlank()) {
            throw new IllegalArgumentException("Bransch får inte vara null eller tomt.");
        }
        if (yearsOfExperience < 0) {
            throw new IllegalArgumentException("Erfarenhet måste vara >= 0.");
        }
        this.name = name;
        this.age = age;
        this.industry = industry;
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Gets the candidate's name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the candidate's age.
     * 
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the candidate's industry.
     * 
     * @return industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Gets the candidate's years of experience.
     * 
     * @return years of experience
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * Returns a string representation of the candidate.
     * 
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("Namn: %s, Ålder: %d, Bransch: %s, Erfarenhet: %d år", name, age, industry,
                yearsOfExperience);
    }
}
