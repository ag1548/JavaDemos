import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public final class Main {

    public static void main(String[] args) { new Main(); }

    public class MsgCounts {

        int info = 0;
        int warn = 0;
        int error = 0;

    }

    public ArrayList<String> ReadFileContents() {
        ArrayList<String> fileContents = new ArrayList<>();
        File inputFile = new File("input.txt");
        try (Scanner reader = new Scanner(inputFile)) {
            while(reader.hasNextLine()) {
                fileContents.add(reader.nextLine());
                if (fileContents.get(fileContents.size() - 1).equals("END")) {
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            System.err.println("ERROR: File does not exist");
        }
        return fileContents;
    }

    public void ParseFileContents(ArrayList<String> fileContents, MsgCounts counts) {
        for (int i = 0; i < fileContents.size(); i++) {
            if (fileContents.get(i).startsWith("INFO")) { counts.info++; }
            if (fileContents.get(i).startsWith("WARN")) { counts.warn++; }
            if (fileContents.get(i).startsWith("ERROR")) { counts.error++; }
        }
    }

    public Main() {

        ArrayList<String> fileContents = ReadFileContents();

        MsgCounts counts = new MsgCounts();
        ParseFileContents(fileContents, counts);

        System.out.println("Summary:");
        System.out.println(String.format("INFO: %d", counts.info));
        System.out.println(String.format("WARN: %d", counts.warn));
        System.out.println(String.format("ERROR: %d", counts.error));
    }

}
