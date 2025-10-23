# Kandidathanteringssystem för Rekryterare

## Översikt

En CLI-baserad Java-applikation för att hantera kandidater, med stöd för filtrering, sortering, loggning och robust inmatningshantering. Utvecklad enligt SOLID-principer, Command-pattern och testad med JUnit/Mockito. Alla publika klasser och metoder är dokumenterade med JavaDoc.

## Funktioner

- Lägg till, ta bort, visa och filtrera kandidater
- Filtrering på bransch, erfarenhet, namn (A-Ö)
- Loggning av viktiga händelser med SLF4J
- Utbyggbar filterstruktur (OCP/DIP)
- Robust inmatningshantering och validering av konstruktorer
- Kommandon implementerade med Command-pattern
- Dynamiska menyer med SubMenu-klassen
- Enhetstester med JUnit och Mockito (mockning av beroenden)
- JavaDoc för alla publika klasser och metoder

## Prerequisites

- Java 21
- Maven 3.8+

## Bygg, Testa och Kör

### Bygg

```sh
mvn clean package
```

### Testa

```sh
mvn test
```

### Kör

```sh
mvn clean package
java -jar target/Kandidathanteringssystem-1.0-SNAPSHOT.jar
```

## Designval

- **SOLID:** Separata klasser för data, logik och filtrering
- **Command-pattern:** Kommandon för CLI-funktioner
- **Stream API:** För filtrering och sortering
- **SLF4J:** Loggning till konsol
- **OCP/DIP:** Nya filter kan läggas till via `CandidateFilter`-interface
- **SubMenu:** Dynamisk hantering av undermenyer
- **Robusthet:** Inmatningsvalidering och null-checks i alla funktioner
- **Testbarhet:** Enhetstester isolerar beroenden med Mockito
- **JavaDoc:** Dokumentation av publika klasser och metoder

## Filstruktur

- `src/main/java/com/example/` - Källkod
- `src/test/java/com/example/` - Tester
- `.github/` - (tidigare AI-agentinstruktioner, kan tas bort från historik)

## Exempel på utbyggnad

- Lägg till nytt filter: Implementera `CandidateFilter` och registrera i CLI
- Lägg till kommando: Implementera en ny Command-klass och lägg till i Menu
- Lägg till test: Skapa testklass i `src/test/java/com/example/` och mocka beroenden
