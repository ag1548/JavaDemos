import java.util.TreeSet;
import java.util.Arrays;

// https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html

// Notice how this is the exact same implementation as HashSet,
// and the only difference between the two is the way it's stored in memory!

public class Main {

    public static void InitByAddingExample() {

        System.out.println("InitByAddingExample");

        TreeSet<Integer> numbers = new TreeSet<Integer>();
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

        TreeSet<Integer> numbers = new TreeSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        numbers.forEach(n -> { System.out.println(n); });
    }

    public static void main(String[] args) {

        InitByAddingExample();
        InitFromListExample();

    }

}