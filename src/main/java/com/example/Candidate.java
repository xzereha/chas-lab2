package com.example;

public class Candidate {
    private String name;
    private int age;
    private String industry;
    private int yearsOfExperience;

    public Candidate(String name, int age, String industry, int yearsOfExperience) {
        this.name = name;
        this.age = age;
        this.industry = industry;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getIndustry() {
        return industry;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return String.format("Namn: %s, Ålder: %d, Bransch: %s, Erfarenhet: %d år", name, age, industry,
                yearsOfExperience);
    }
}
