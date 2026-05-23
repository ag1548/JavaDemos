import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void SomeFunction(Tuple t) {
        System.out.println("a: " + t.a + ", b: " + t.b);
    }

    public static void main(String[] args) {

        // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
        ArrayList<Tuple> numbers = new ArrayList<Tuple>(Arrays.asList(
            new Tuple(1, 2),
            new Tuple(2, 3),
            new Tuple(3, 4),
            new Tuple(4, 5),
            new Tuple(5, 6)
        ));

        // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#forEach-java.util.function.Consumer-
        // "For all numbers that are in the array, do this function that takes a tuple of 2 integers"
        numbers.forEach(t -> { System.out.println("a: " + t.a + ", b: " + t.b); });

        // Can also be written as:
        // Again, "For all numbers that are in the array, do this function", but instead of defining the lambda (which is a function) we are directly telling "forEach" which function to use (because the SomeFunction function takes 1 int argument)
        numbers.forEach(Main::SomeFunction);
    }

}

class Tuple {

    public int a, b;

    Tuple(int a, int b) {
        this.a = a;
        this.b = b;
    }

}
