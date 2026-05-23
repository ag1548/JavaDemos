import java.util.HashMap;

// https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

// Note how this is exactly like a python dictionary
// e.g.
//
// ```python
//
//     def main():
//         mydict = {}
//         mydict["SomeKey"] = 5
//         mydict["SomeSecondKey"] = 7
//
//     if __name__ == "__main__":
//         main()
//
// ```

public class Main {

    public static void main(String[] args) {

        HashMap<String, Integer> myDict = new HashMap<String, Integer>();

        myDict.put("SomeKey", 5);
        myDict.put("SomeSecondKey", 7);

        myDict.forEach((k, v) ->  {
            System.out.println("The key is " + k + ", and the value is " + v);
        });

    }

}