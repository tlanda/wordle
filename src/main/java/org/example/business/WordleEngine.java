package org.example.business;

import org.example.view.ColoredChar;
import org.example.view.ConsoleColor;
import org.example.view.ConsoleColors;
import org.example.view.InputReader;

import java.util.ArrayList;
import java.util.List;

public class WordleEngine {

    private String word;
    private InputReader inputReader = new InputReader();

    public void startGame() {
        boolean correct = false;
        word = "POTET".toUpperCase();
        int wordLength = word.length();

        for (int i = 0; i < wordLength; i++) {
            String input = inputReader.readLine(wordLength).toUpperCase();
            input = cleanInput(input, wordLength);
            List<CharacterWithMatch> characterWithMatches = matchInputWithWord(input.toCharArray());
            List<ColoredChar> coloredChars = toColoredChars(characterWithMatches);
            inputReader.printWithColor(coloredChars);

            correct = characterWithMatches.stream().map(CharacterWithMatch::match).allMatch(m -> m.equals(Match.MATCH));

            if (correct) {
                System.out.println("Correct in " + (i + 1) + " trie(s)!");
                break;
            }
        }
        if (!correct) {
            System.out.println(ConsoleColors.RED + "FAILED!" + ConsoleColors.RESET);
        }
    }

    private List<CharacterWithMatch> matchInputWithWord(char[] input) {
        List<CharacterWithMatch> characterWithMatch = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == word.charAt(i)) {
                characterWithMatch.add(new CharacterWithMatch(input[i], Match.MATCH));
            } else if (word.indexOf(input[i]) > 0) {
                characterWithMatch.add(new CharacterWithMatch(input[i], Match.WRONG_POSITION));
            } else {
                characterWithMatch.add(new CharacterWithMatch(input[i], Match.NO_MATCH));
            }
        }
        return characterWithMatch;
    }

    private List<ColoredChar> toColoredChars(List<CharacterWithMatch> characterWithMatches) {
        return characterWithMatches.stream()
                .map(c -> new ColoredChar(c.character(), toColor(c.match())))
                .toList();
    }

    private ConsoleColor toColor(Match match) {
        return switch (match) {
            case MATCH -> ConsoleColor.GREEN;
            case NO_MATCH -> ConsoleColor.NORMAL;
            case WRONG_POSITION -> ConsoleColor.YELLOW;
        };
    }

    private String cleanInput(String input, int targetLength) {
        if (input.length() < targetLength) {
            return cleanInput(input + " ",targetLength);
        } else {
            return input.substring(0, targetLength);
        }
    }


}
