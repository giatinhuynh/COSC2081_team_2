package exceptions;

import java.util.Scanner;

public class InputValidation {

    private final Scanner scanner = new Scanner(System.in);

    public boolean getBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if ("true".equals(input) || "false".equals(input)) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }
    }

    public double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid double value.");
            }
        }
    }

    public int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer value.");
            }
        }
    }

    public String idValidation(String prefix, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.startsWith(prefix) && input.length() == prefix.length() + 6) {
                try {
                    Long.parseLong(input.substring(prefix.length())); // Check if the remaining part is a valid 6-digit number
                    return input;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID. Please ensure the ID after the prefix consists of 6 digits.");
                }
            } else {
                System.out.println("Invalid ID format. Please ensure the ID starts with " + prefix + " and is followed by 6 digits.");
            }
        }
    }

    public String getString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter a non-empty string.");
            }
        }
    }

    public String getUserInfo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter a non-empty string.");
            }
        }
    }
}
