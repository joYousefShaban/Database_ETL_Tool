package com.example.demo.services.logging;


@SuppressWarnings("unused")
public class ANSI {

    // Reset
    public static final String RESET = "\u001B[0m";  // Text Reset
    // Regular Colors
    public static final String BLACK = "\u001B[0;30m";   // BLACK
    public static final String RED = "\u001B[0;31m";     // RED
    public static final String GREEN = "\u001B[0;32m";   // GREEN
    public static final String YELLOW = "\u001B[0;33m";  // YELLOW
    public static final String BLUE = "\u001B[0;34m";    // BLUE
    public static final String PURPLE = "\u001B[0;35m";  // PURPLE
    public static final String CYAN = "\u001B[0;36m";    // CYAN
    public static final String WHITE = "\u001B[0;37m";   // WHITE
    public static final String ORANGE = "\u001B[38;5;208m";   // ORANGE
    public static final String PINK = "\u001B[38;5;205m";     // PINK
    public static final String BROWN = "\u001B[38;5;94m";     // BROWN
    public static final String GRAY = "\u001B[38;5;240m";     // GRAY
    public static final String LIGHT_BLUE = "\u001B[38;5;75m"; // LIGHT BLUE
    public static final String LAVENDER = "\u001B[38;5;183m"; // LAVENDER
    public static final String TEAL = "\u001B[38;5;29m";      // TEAL
    public static final String MINT = "\u001B[38;5;121m";     // MINT
    // Bold
    public static final String BLACK_BOLD = "\u001B[1;30m";  // BLACK
    public static final String RED_BOLD = "\u001B[1;31m";    // RED
    public static final String GREEN_BOLD = "\u001B[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\u001B[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\u001B[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\u001B[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\u001B[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\u001B[1;37m";  // WHITE
    public static final String ORANGE_BOLD = "\u001B[1;38;5;208m";   // ORANGE
    public static final String PINK_BOLD = "\u001B[1;38;5;205m";     // PINK
    public static final String BROWN_BOLD = "\u001B[1;38;5;94m";     // BROWN
    public static final String GRAY_BOLD = "\u001B[1;38;5;240m";     // GRAY
    public static final String LIGHT_BLUE_BOLD = "\u001B[1;38;5;75m"; // LIGHT BLUE
    public static final String LAVENDER_BOLD = "\u001B[1;38;5;183m"; // LAVENDER
    public static final String TEAL_BOLD = "\u001B[1;38;5;29m";      // TEAL
    public static final String MINT_BOLD = "\u001B[1;38;5;121m";     // MINT
    // Underline
    public static final String BLACK_UNDERLINED = "\u001B[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\u001B[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\u001B[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\u001B[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\u001B[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\u001B[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\u001B[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\u001B[4;37m";  // WHITE
    public static final String ORANGE_UNDERLINED = "\u001B[4;38;5;208m";   // ORANGE
    public static final String PINK_UNDERLINED = "\u001B[4;38;5;205m";     // PINK
    public static final String BROWN_UNDERLINED = "\u001B[4;38;5;94m";     // BROWN
    public static final String GRAY_UNDERLINED = "\u001B[4;38;5;240m";     // GRAY
    public static final String LIGHT_BLUE_UNDERLINED = "\u001B[4;38;5;75m"; // LIGHT BLUE
    public static final String LAVENDER_UNDERLINED = "\u001B[4;38;5;183m"; // LAVENDER
    public static final String TEAL_UNDERLINED = "\u001B[4;38;5;29m";      // TEAL
    public static final String MINT_UNDERLINED = "\u001B[4;38;5;121m";     // MINT
    // Background
    public static final String BLACK_BACKGROUND = "\u001B[40m";  // BLACK
    public static final String RED_BACKGROUND = "\u001B[41m";    // RED
    public static final String GREEN_BACKGROUND = "\u001B[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\u001B[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\u001B[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\u001B[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\u001B[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\u001B[47m";  // WHITE
    public static final String ORANGE_BACKGROUND = "\u001B[48;5;208m";   // ORANGE
    public static final String PINK_BACKGROUND = "\u001B[48;5;205m";     // PINK
    public static final String BROWN_BACKGROUND = "\u001B[48;5;94m";     // BROWN
    public static final String GRAY_BACKGROUND = "\u001B[48;5;240m";     // GRAY
    public static final String LIGHT_BLUE_BACKGROUND = "\u001B[48;5;75m"; // LIGHT BLUE
    public static final String LAVENDER_BACKGROUND = "\u001B[48;5;183m"; // LAVENDER
    public static final String TEAL_BACKGROUND = "\u001B[48;5;29m";      // TEAL
    public static final String MINT_BACKGROUND = "\u001B[48;5;121m";     // MINT
    // High Intensity
    public static final String BLACK_BRIGHT = "\u001B[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\u001B[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\u001B[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\u001B[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\u001B[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\u001B[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\u001B[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\u001B[0;97m";  // WHITE
    public static final String ORANGE_BRIGHT = "\u001B[0;38;5;208m";   // ORANGE
    public static final String PINK_BRIGHT = "\u001B[0;38;5;205m";     // PINK
    public static final String BROWN_BRIGHT = "\u001B[0;38;5;94m";     // BROWN
    public static final String GRAY_BRIGHT = "\u001B[0;38;5;240m";     // GRAY
    public static final String LIGHT_BLUE_BRIGHT = "\u001B[0;38;5;75m"; // LIGHT BLUE
    public static final String LAVENDER_BRIGHT = "\u001B[0;38;5;183m"; // LAVENDER
    public static final String TEAL_BRIGHT = "\u001B[0;38;5;29m";      // TEAL
    public static final String MINT_BRIGHT = "\u001B[0;38;5;121m";     // MINT
    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\u001B[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\u001B[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\u001B[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\u001B[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\u001B[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\u001B[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\u001B[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\u001B[1;97m"; // WHITE
    public static final String ORANGE_BOLD_BRIGHT = "\u001B[1;38;5;208m";   // ORANGE
    public static final String PINK_BOLD_BRIGHT = "\u001B[1;38;5;205m";     // PINK
    public static final String BROWN_BOLD_BRIGHT = "\u001B[1;38;5;94m";     // BROWN
    public static final String GRAY_BOLD_BRIGHT = "\u001B[1;38;5;240m";     // GRAY
    public static final String LIGHT_BLUE_BOLD_BRIGHT = "\u001B[1;38;5;75m"; // LIGHT BLUE
    public static final String LAVENDER_BOLD_BRIGHT = "\u001B[1;38;5;183m"; // LAVENDER
    public static final String TEAL_BOLD_BRIGHT = "\u001B[1;38;5;29m";      // TEAL
    public static final String MINT_BOLD_BRIGHT = "\u001B[1;38;5;121m";     // MINT
    private ANSI() {
    }

    public static String colour(String message, String colorCode) {
        return colorCode + message + RESET;
    }
}
