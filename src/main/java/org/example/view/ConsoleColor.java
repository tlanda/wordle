package org.example.view;

public enum ConsoleColor {
    GREEN(ConsoleColors.GREEN),
    YELLOW(ConsoleColors.YELLOW),
    NORMAL(""),
    GREY(ConsoleColors.BLACK_BRIGHT);

    private String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    public String getColorCode() {
        return code;
    }

}
