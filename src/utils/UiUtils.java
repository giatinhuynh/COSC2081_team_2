package utils;

import java.io.IOException;

public class UiUtils {
    public static void printSeparator(int width) {
        System.out.println("=".repeat(width));
    }

    public static void printCentered(String text, int width) {
        int paddingSize = (width - text.length()) / 2;
        for (int i = 0; i < paddingSize; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
