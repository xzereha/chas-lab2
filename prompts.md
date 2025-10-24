# Prompts

- Promptade AI att generera start koden genom att ge den bakgrundsbeskrivningen.

Den generarade en main, Candidat och CandidatRepository.
Dessas följde vagt beskrivningen men inte särskillt snyggt.

- Promptat AI för att snygga till tidigare output

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

```test
Refactor all tests in the project to use the naming convention

function_state_expected
```

Detta fungerade felfritt.
