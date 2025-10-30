# Prompts

- Promptade AI att generera start koden genom att ge den bakgrundsbeskrivningen.

Den generarade en main, Candidat och CandidatRepository och basic filter.
Dessas följde vagt beskrivningen men inte särskillt snyggt, hade bland annat all meny kod och diverse hjälp logik i main.

Mitt huvudsakliga problem med detta var att main var kaos så jag ville fixa det direkt..

- Promptat AI för att snygga till tidigare output, samt att skriva koden lite bättre. Den tidigare genererade koden gjorde massa antagenden om sin input som jag inte ville ha.

```txt
More steps:

Refactor out the Menu to not have so much logic in main.
Make all functions more robust, check for null and so on
JavaDoc all functions
```

AIn Skapade en meny klass men en jäkla lång input handling metod.
Denna bröt jag senare ut till olika commands via command pattern.

- Promptat AI att generera diverse testfall.

Exempel prompt för tests

```txt
Add tests for the SubMenu.
It should test that it correctly executes commands based on the input (Mock some commands).
It should test that it gracefully exists if the steam ends.
And it should test that it respects the returns of the commands
```

AIn spottade ut en bunt test cases. Dessa fungerade som förväntat och behövde nästan aldrig ändras.

- Bad AI formatera om namnet på tester så att dem var enhetliga

```txt
Refactor all tests in the project to use the naming convention

function_state_expected
```

Detta fungerade felfritt.

- Jag provade även en alternativ prompt för testning istället för den ovanstående, för att se hur mycket prompten påverkade resultatet.

```txt
Generate test cases for my submenu
```

Detta generarade korrekta tester fast med mycket färre tester av edgecases, den testade endast för korrekt input med ett kommando, inkorrekt input med ett kommando, och end of stream.
Så den hade exempelvis inga tester för vad som händer ifall där inte finns några registrerade kommandon, eller att den faktisk gör rätt kommando ifall där finns flera registrerade.

Min totala erfarenhet av LLM användningen är att den börjar väldigt slarvigt och rörigt men ju mer kontext den får ju bättre blir resultatet då den lär sig vilken "stil" man vill att projektet skall ha.
