import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) { new Main(); }
    Main() { RunGameLoop(); }

    public int currentRandomNumber = -1;
    public void GenerateNewRandomNumber() {
        System.out.print("Generating new random number...");

        Random rand = new Random();
        final int RANGE = 100;
        this.currentRandomNumber = rand.nextInt(RANGE + 1);

        System.out.println("DONE!");
    }

    public int GetUserInput(Scanner in) {

        int guess = -1;
        while (true) {
            try {
                System.out.print("What's your next guess?? ");
                guess = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Oops! Something went wrong...");
                in.next();
            }
        }

        return guess;
    }

    public boolean EvaluateGuess(int guess) {

        if (this.currentRandomNumber == guess)
            return true;

        System.out.println("Too " + (this.currentRandomNumber < guess ? "high" : "low") + "...");

        final int diff = Math.abs(guess - this.currentRandomNumber);
        if (diff <= 5)
            System.out.println("HOT");
        else if (diff <= 10)
            System.out.println("Warm");
        else
            System.out.println("Cold");

        return false;
    }

    public void RunGameLoop() {

        Scanner in = new Scanner(System.in);
        boolean win;

        GenerateNewRandomNumber();
        int attempts = 0;
        do {
            attempts++;
            final int currentGuess = GetUserInput(in);
            win = EvaluateGuess(currentGuess);
            if (!win) { System.out.println("Try again!"); }
        } while (!win);

        System.out.println("You win!");
        System.out.println("Attempts: " + attempts);
    }

}
