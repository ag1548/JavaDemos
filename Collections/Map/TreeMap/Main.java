import java.util.TreeMap;

// https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html

// Note how this is the same as the HashMap, except for the way
// it is stored in memory!

public class Main {

    public static void main(String[] args) {

        TreeMap<String, Integer> myDict = new TreeMap<String, Integer>();

        myDict.put("SomeKey", 5);
        myDict.put("SomeSecondKey", 7);

        myDict.forEach((k, v) ->  {
            System.out.println("The key is " + k + ", and the value is " + v);
        });

    }

}