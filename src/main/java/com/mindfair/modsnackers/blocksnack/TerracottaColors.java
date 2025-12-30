package com.mindfair.modsnackers.blocksnack;

public enum TerracottaColors {
    NONE, BLACK, BLUE, BROWN, CYAN, GRAY, GREEN, LIGHT_BLUE, LIGHT_GRAY,
    LIME, MAGENTA, ORANGE, PINK, PURPLE, RED, WHITE, YELLOW;

    public static String getNameWithColorPrefix(String baseName, TerracottaColors color) {
        return (color == TerracottaColors.NONE ? baseName : String.format("%s_%s", color.name().toLowerCase(), baseName));
    }
}

