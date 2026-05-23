import java.util.Random;
import java.util.Scanner;

public final class Main {

    public enum HAND {
        ROCK,
        PAPER,
        SCISSORS,
        INVALID,
    }

    public static void main(String[] args) { new Main(); }
    Main() { RunGameLoop(); }

    Random rand;

    public HAND GetUserInput(Scanner in) {
        while (true) {
            try {
                System.out.print("rock/paper/scissors?? ");

                String choice = in.next().toLowerCase();
                if (choice.equals("rock")) return HAND.ROCK;
                if (choice.equals("paper")) return HAND.PAPER;
                if (choice.equals("scissors")) return HAND.SCISSORS;

            } catch (Exception e) {
                System.out.println("Oops! Something went wrong...");
                // in.next();
            }
        }
    }

    class Score {

        public int score;

        Score(int score) { this.score = score; }

        public void increment() { this.score++; }

        @Override
        public String toString() { return String.valueOf(this.score); }
    }


    public HAND GetCPUInput() {
        return HAND.values()[this.rand.nextInt(3)];
    }

    void DetermineRoundWinner(
        final int round,
        HAND usrHand,
        Score usrWins,
        HAND cpuHand,
        Score cpuWins
    ) {

        String roundStr = String.format("Round %d", round);
        String usrStr = String.format("You=%s", usrHand.toString());
        String cpuStr = String.format("CPU=%s", cpuHand.toString());

        // Handle Draw
        if (usrHand == cpuHand) {
            System.out.println(String.format("%s: %s, %s -> Draw (%s-%s)", roundStr, usrStr, cpuStr, usrWins, cpuWins));
            return;
        }

        switch (usrHand) {
            case HAND.ROCK -> {
                if (cpuHand == HAND.PAPER)
                    {cpuWins.increment(); System.out.println(String.format("%s: %s, %s -> You Lose! (%s-%s)", roundStr, usrStr, cpuStr, usrWins, cpuWins));}
                else
                    {usrWins.increment(); System.out.println(String.format("%s: %s, %s -> You Win! (%s-%s)", roundStr, usrStr, cpuStr, usrWins, cpuWins));}
            }
            case HAND.PAPER -> {
                if (cpuHand == HAND.ROCK)
                    {usrWins.increment(); System.out.println(String.format("%s: %s, %s -> You Win! (%s-%s)", roundStr, usrStr, cpuStr, usrWins, cpuWins));}
                else
                    {cpuWins.increment(); System.out.println(String.format("%s: %s, %s -> You Lose! (%s-%s)", roundStr, usrStr, cpuStr, usrWins, cpuWins));}
            }
            case HAND.SCISSORS -> {
                if (cpuHand == HAND.ROCK)
                    {cpuWins.increment(); System.out.println(String.format("%s: %s, %s -> You Lose! (%s-%s)", roundStr, usrStr, cpuStr, usrWins, cpuWins));}
                else
                    {usrWins.increment(); System.out.println(String.format("%s: %s, %s -> You Win! (%s-%s)", roundStr, usrStr, cpuStr, usrWins, cpuWins));}
            }
            default -> {
                System.err.println("ERROR");
                System.exit(1);
            }
        }
    }

    public void RunGameLoop() {

        Scanner in = new Scanner(System.in);
        boolean gameover = false;
        this.rand = new Random();

        Score usrWins = new Score(0);
        Score cpuWins = new Score(0);
        int round = 1;
        while (!gameover) {
            DetermineRoundWinner(round++, GetUserInput(in), usrWins, GetCPUInput(), cpuWins);

            if (cpuWins.score == 3 || usrWins.score == 3)
                gameover = true;
        }
        System.out.println(String.format("Final: %s-%s", usrWins, cpuWins));
    }

}
