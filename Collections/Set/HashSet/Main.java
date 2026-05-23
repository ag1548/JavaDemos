import java.util.HashSet;
import java.util.Arrays;

// https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html

// Notice how this is the exact same implementation as Lists,
// but instead of being lists, these are sets

public class Main {

    public static void InitByAddingExample() {

        System.out.println("InitByAddingExample");

        HashSet<Integer> numbers = new HashSet<Integer>();
        System.out.println("numbers.isEmpty() ? " + numbers.isEmpty());

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("numbers.isEmpty() ? " + numbers.isEmpty());
        numbers.forEach(n -> { System.out.println(n); });
    }

    public static void InitFromListExample() {

        System.out.println("InitFromListExample");

        HashSet<Integer> numbers = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        numbers.forEach(n -> { System.out.println(n); });
    }

    public static void main(String[] args) {

        InitByAddingExample();
        InitFromListExample();

    }

}