# Kandidathanteringssystem för Rekryterare

## Översikt

En CLI-baserad Java-applikation för att hantera kandidater, med stöd för filtrering, sortering och loggning. Utvecklad enligt SOLID-principer och testad med JUnit/Mockito.

## Funktioner

- Lägg till, ta bort, visa och filtrera kandidater
- Filtrering på bransch, erfarenhet, namn (A-Ö)
- Loggning av viktiga händelser med SLF4J
- Utbyggbar filterstruktur (OCP/DIP)
- Enhetstester med JUnit och Mockito

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
java -jar target/inlm2-1.0-SNAPSHOT.jar
```

## Designval

- **SOLID:** Separata klasser för data, logik och filtrering
- **Stream API:** För filtrering och sortering
- **SLF4J:** Loggning till konsol
- **OCP/DIP:** Nya filter kan läggas till via `CandidateFilter`-interface
- **Testbarhet:** Enhetstester isolerar beroenden med Mockito

## Filstruktur

- `src/main/java/com/example/` - Källkod
- `src/test/java/com/example/` - Tester
- `.github/copilot-instructions.md` - AI-agentinstruktioner

## Exempel på utbyggnad

- Lägg till nytt filter: Implementera `CandidateFilter` och registrera i CLI
- Lägg till test: Skapa testklass i `src/test/java/com/example/`
