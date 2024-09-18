package org.example.view;

import java.util.List;
import java.util.Scanner;

public class InputReader {

    public String readLine(int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter " + n + " characters");
        String input = scanner.nextLine();
        return input;
    }

    public void printWithColor(List<ColoredChar> coloredChars) {
        for (ColoredChar c : coloredChars) {
            System.out.print(c.color().getColorCode() + c.character() + ConsoleColors.RESET);
        }
        System.out.println();
    }

}
