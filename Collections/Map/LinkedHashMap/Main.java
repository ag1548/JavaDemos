import java.util.LinkedHashMap;

// https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html

// Again, note how this is the exact same as the HashMap and LinkedHashMap,
// except for the way it is stored in memory!

public class Main {

    public static void main(String[] args) {

        LinkedHashMap<String, Integer> myDict = new LinkedHashMap<String, Integer>();

        myDict.put("SomeKey", 5);
        myDict.put("SomeSecondKey", 7);

        myDict.forEach((k, v) ->  {
            System.out.println("The key is " + k + ", and the value is " + v);
        });

    }

}