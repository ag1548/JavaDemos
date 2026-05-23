import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.function.Function;

public class Main {

    public static void SomeFunction(int n) {
        System.out.println(n);
    }

    public static void main(String[] args) {

        // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
        ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

        // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#forEach-java.util.function.Consumer-
        // "For all numbers that are in the array, do this function"
        //
        // Structure:
        //   (parameters, ...) -> { ...function body... }
        //
        // In the forEach, we can ignore parenthesis
        //   (n) -> { System.out.println(n); }
        //
        numbers.forEach(n -> { System.out.println(n); });

        // Can also be written as:
        // Again, "For all numbers that are in the array, do this function", but instead of defining the lambda (which is a function) we are directly telling "forEach" which function to use (because the SomeFunction function takes 1 int argument)
        numbers.forEach(Main::SomeFunction);
    }

}