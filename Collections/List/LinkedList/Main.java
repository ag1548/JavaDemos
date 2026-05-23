import java.util.LinkedList;
import java.util.Arrays;

// https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html

// NOTE: Exactly the same as ArrayList, except for the way it is stored in memory!

public class Main {

    public static void InitByAddingExample() {

        System.out.println("InitByAddingExample");

        LinkedList<Integer> numbers = new LinkedList<Integer>();
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

        LinkedList<Integer> numbers = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        numbers.forEach(n -> { System.out.println(n); });
    }

    public static void main(String[] args) {

        InitByAddingExample();
        InitFromListExample();

    }

}