import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

// 1 Support commands: add, list, done, remove, exit
// 2 Store tasks in ArrayList with auto-increment IDs.
// 3 Print tasks as: #id [ ] desc or #id [X] desc.
// 4 Validate invalid commands and IDs.

public final class Main {

    public static void main(String[] args) { new Main(); }

    public Scanner in;

    class TodoItem {
        public int ID;
        public String todo;
        public boolean done = false;

        public TodoItem(int ID, String todo) { this.ID = ID; this.todo = todo; }

        public String toString() { return String.format("#%d [%s] %s", ID, done ? 'X' : ' ', todo); }
    }

    public ArrayList<TodoItem> todoList = new ArrayList<>();
    public int currentID = 0;

    public boolean ValidateInputCommand(String command) {
        final ArrayList<String> availableCommands = new ArrayList<>(Arrays.asList("add", "list", "done", "remove", "exit"));
        for (int i = 0; i < availableCommands.size(); i++)
            if (availableCommands.get(i).equals(command))
                return true;
        return false;
    }

    public ArrayList<String> GetUserInput() {
        System.out.println("------------------------------");
        System.out.println("add, list, done, remove, exit");
        System.out.print("TODO MGR Command >>> ");

        ArrayList<String> commandTokens = new ArrayList<>();
        while (true) {

            String command = in.nextLine().toLowerCase();

            // TODO: Assert command length > 0

            StringTokenizer st = new StringTokenizer(command);

            while (st.hasMoreTokens()) commandTokens.add(st.nextToken());

            if (ValidateInputCommand(commandTokens.get(0))) break;

            System.out.println(String.format("Unrecognized command: '%s'", commandTokens.get(0)));
            System.out.print("TODO MGR Command >>> ");

            commandTokens.clear();
        }

        return commandTokens;
    }

    public void Add(ArrayList<String> command) {

        // TODO: Validate 'add' command inputs

        StringBuilder builder = new StringBuilder();
        builder.append(command.get(1));
        for (int i = 2; i < command.size(); i++)
            builder.append(" ").append(command.get(i));

        TodoItem newTodoItem = new TodoItem(currentID++, builder.toString());
        todoList.add(newTodoItem);

        System.out.println(String.format("Added #%d: %s", newTodoItem.ID, newTodoItem.todo));
    }

    public void List() {
        System.out.println("==============================");
        todoList.forEach(t -> { System.out.println(t.toString()); });
        System.out.println("==============================");
    }

    public void Done(ArrayList<String> command) {

        if (command.size() != 2) {
            System.out.println("Invalid DONE command: needs 1 argument");
            return;
        }

        int id = Integer.parseInt(command.get(1));
        if (id >= command.size()) {
            System.out.println("Invalid DONE command: Invalid ID");
            return;
        }

        todoList.get(id).done = true;

        System.out.println(String.format("Marked done: #%d", todoList.get(id).ID));
    }

    public void Remove(ArrayList<String> command) {

        if (todoList.isEmpty()) {
            System.out.println("Invalid REMOVE command: Nothing to remove");
            return;
        }

        if (command.size() != 2) {
            System.out.println("Invalid REMOVE command: needs 1 argument");
            return;
        }

        int id = Integer.parseInt(command.get(1));
        if (id >= command.size()) {
            System.out.println("Invalid DONE command: Invalid ID");
            return;
        }

        todoList.remove(id);
        for (int i = id; i < todoList.size(); i++)
            todoList.get(i).ID--;
    }

    public void PerformCommand(ArrayList<String> command) {
        switch (command.get(0)) {
            case "list"   -> { List();                  }
            case "add"    -> { Add(command);    List(); }
            case "done"   -> { Done(command);   List(); }
            case "remove" -> { Remove(command); List(); }
        }
    }

    public Main() {

        in = new Scanner(System.in);

        while (true) {

            ArrayList<String> command = GetUserInput();

            if (command.get(0).equals("exit")) break;

            PerformCommand(command);
        }

        System.out.println("TODO MGR DONE");
    }

}
