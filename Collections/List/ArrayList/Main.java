import java.util.ArrayList;
import java.util.Arrays;

// https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

public class Main {

    public static void InitByAddingExample() {

        System.out.println("InitByAddingExample");

        ArrayList<Integer> numbers = new ArrayList<Integer>();
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

        ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        numbers.forEach(n -> { System.out.println(n); });
    }

    public static void main(String[] args) {

        InitByAddingExample();
        InitFromListExample();

    }

}