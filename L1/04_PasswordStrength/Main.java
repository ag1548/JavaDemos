import java.util.Random;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) { new Main(); }

    public String GetUserInput() {
        return new Scanner(System.in).next();
    }

    public boolean CheckLength(String str) {
        return str.length() >= 8;
    }

    public boolean CheckRange(String str, char start, char end) {
        for (int pos = 0; pos < str.length(); pos++) {
            for (int letter = (int) start; letter <= (int) end; letter++) {
                if (str.charAt(pos) == letter) {
                    return true;
                }
            }
        }
        return false;
    }

    public String GetRank(int strength) {
        switch (strength) {
            case 0 -> { return "Weak";          }
            case 1 -> { return "Weak-Medium";   }
            case 2 -> { return "Medium";        }
            case 3 -> { return "Medium-Strong"; }
            case 4 -> { return "Strong";        }
        }
        return "";
    }

    public void PrintRandomChar(int rangeStart, int rangeEnd) {

        if (rangeStart == rangeEnd) return;

        Random rand = new Random();
        int index = rand.nextInt((rangeEnd - rangeStart));
        System.out.print(String.format("%c", (char)(index + (int) rangeStart)));
    }

    public void PrintSuggestion(
        String password,
        boolean length,
        boolean uppercase,
        boolean lowercase,
        boolean digit,
        boolean specialcharacter1,
        boolean specialcharacter2,
        boolean specialcharacter3,
        boolean specialcharacter4
    ) {

        System.out.print(String.format("Suggestion: Try '%s", password));

        if (!length)    { for (int i = 0; i < (8 - password.length()); i++) PrintRandomChar((int) '!', (int) '~'); }
        if (!uppercase) { PrintRandomChar((int) 'A', (int) 'Z'); }
        if (!lowercase) { PrintRandomChar((int) 'a', (int) 'z'); }
        if (!digit)     { PrintRandomChar((int) '0', (int) '9'); }
        if (!(specialcharacter1 || specialcharacter2 || specialcharacter3 || specialcharacter4)) {
            Random rand = new Random();
            int choice = rand.nextInt(4);
            switch (choice) {
                case 0 -> {PrintRandomChar((int) '!', (int) '/');}
                case 1 -> {PrintRandomChar((int) ':', (int) '@');}
                case 2 -> {PrintRandomChar((int) '[', (int) '`');}
                case 3 -> {PrintRandomChar((int) '~', (int) '~');}
            }
        }

        System.out.println("'");
    }

    public Main() {

        System.out.print("Choice of password? ");

        String password = GetUserInput();

        boolean printedMissing = false;

        boolean length = CheckLength(password);
        boolean uppercase = CheckRange(password, 'A', 'Z');
        boolean lowercase = CheckRange(password, 'a', 'z');
        boolean digit = CheckRange(password, '0', '9');
        boolean specialcharacter1 = CheckRange(password, '!', '/');
        boolean specialcharacter2 = CheckRange(password, ':', '@');
        boolean specialcharacter3 = CheckRange(password, '[', '`');
        boolean specialcharacter4 = CheckRange(password, '~', '~');

        int strength = 0;
        strength += length            ? 1 : 0;
        strength += uppercase         ? 1 : 0;
        strength += lowercase         ? 1 : 0;
        strength += digit             ? 1 : 0;
        strength += (specialcharacter1 || specialcharacter2 || specialcharacter3 || specialcharacter4) ? 1 : 0;

        System.out.println(String.format("Strength: %d/5 (%s)", strength, GetRank(strength)));

        if (!length)    { if(!printedMissing) System.out.print("Missing: "); else System.out.print(", "); System.out.print("Length >= 8");      printedMissing = true; }
        if (!uppercase) { if(!printedMissing) System.out.print("Missing: "); else System.out.print(", "); System.out.print("Uppercase Letter"); printedMissing = true; }
        if (!lowercase) { if(!printedMissing) System.out.print("Missing: "); else System.out.print(", "); System.out.print("Lowercase Letter"); printedMissing = true; }
        if (!digit)     { if(!printedMissing) System.out.print("Missing: "); else System.out.print(", "); System.out.print("Digit");            printedMissing = true; }
        if (!(specialcharacter1 || specialcharacter2 || specialcharacter3 || specialcharacter4)) {
            if(!printedMissing) System.out.print("Missing: "); else System.out.print(", "); System.out.print("Special Char"); printedMissing = true;
        }

        if (printedMissing) System.out.println();

        PrintSuggestion(password, length, uppercase, lowercase, digit, specialcharacter1, specialcharacter2, specialcharacter3, specialcharacter4);
    }

}
