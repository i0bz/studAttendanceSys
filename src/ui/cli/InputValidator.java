package ui.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private final Scanner input;

    InputValidator(Scanner input) {
        this.input = input;
    }

    int safeIntInput() {
        int tmp;
        try {
            tmp = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            return -1;
        }
        return tmp;
    }

    double safeDoubleInput() {
        int tmp;
        try {
            tmp = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            return -1;
        }
        return tmp;
    }
}
