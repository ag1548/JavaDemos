import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) { Main main = new Main(); main.MainRunLoop(); }

    public HashMap<String, Consumer<String>> COMMANDS;

    public Scanner in;

    class Book {

        String isbn;
        String title;
        String author;

        Book(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Book other = (Book) obj;
            return this.isbn.equals(other.isbn)
                   && this.title.equals(other.title)
                   && this.author.equals(other.author);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isbn, title, author);
        }

        @Override
        public String toString() {
            return String.format("%s %s %s", isbn, title, author);
        }
    }

    class Member {

        String id;
        String name;

        Member(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Member other = (Member) obj;
            return this.id.equals(other.id)
                   && this.name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return String.format("%s %s", id, name);
        }
    }

    class Loan {

        String isbn;
        String memberId;
        Date dueDate;

        Loan(String isbn, String memberId, Date dueDate) {
            this.isbn = isbn;
            this.memberId = memberId;
            this.dueDate = dueDate;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Loan other = (Loan) obj;
            return this.isbn.equals(other.isbn)
                   && this.memberId.equals(other.memberId)
                   && this.dueDate.equals(other.dueDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isbn, memberId, dueDate);
        }

        @Override
        public String toString() {
            return String.format("%s %s %s", isbn, memberId, dueDate.toString());
        }
    }

    HashSet<Book> books = new HashSet<>();
    HashSet<Member> members = new HashSet<>();
    HashSet<Loan> loans = new HashSet<>();

    /**
     *
     * Adds a book to the library.
     *
     * @param command The whole command as given on the command prompt.
     *                E.g. "add-book 9780134685991 Effective_Java Bloch"
     */
    public void AddBook(String command) {

        // Validate
        // TODO: Get rid of assumptions here...
        if (command.split(" ").length != 4) {
            System.out.println("ERROR: Invalid `add-book` format:\n\targs: ");
            for (String token : command.split(" "))
                System.out.print(String.format("%s", token));
            System.out.println();
            return;
        }

        // Perform
        String isbn = command.split(" ")[1];
        String title = command.split(" ")[2];
        String author = command.split(" ")[3];

        Book b = new Book(isbn, title, author);
        if (books.contains(b))
            System.out.println("ERROR: Book already exists!");
        else
            books.add(b);
    }

    public void AddMember(String command) {

        // Validate
        // TODO: Get rid of assumptions here...
        if (command.split(" ").length != 3) {
            System.out.println("ERROR: Invalid `add-member` format:\n\targs: ");
            for (String token : command.split(" "))
                System.out.print(String.format("%s", token));
            System.out.println();
            return;
        }

        // Perform
        String id = command.split(" ")[1];
        String name = command.split(" ")[2];

        Member m = new Member(id, name);
        if (members.contains(m))
            System.out.println("ERROR: Member already exists!");
        else
            members.add(m);
    }

    public void Loan(String command) {
        // Validate
        // TODO: Get rid of assumptions here...
        if (command.split(" ").length != 4) {
            System.out.print("ERROR: Invalid `loan` format:\n\targs: ");
            for (String token : command.split(" "))
                System.out.print(String.format("%s", token));
            System.out.println();
            return;
        }

        // Perform
        String isbn = command.split(" ")[1];
        String memberId = command.split(" ")[2];
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = df.parse(command.split(" ")[3]);
            Loan l = new Loan(isbn, memberId, dueDate);
            if (loans.contains(l))
                System.out.println("ERROR: Loan exists!");
            else
                loans.add(l);
        } catch (ParseException e) {
            System.out.print("ERROR: Invalid Date:\n\targ:");
            System.out.print(command.split(" ")[3]);
        }
    }

    public void Return(String command) {
        // Validate
        // TODO: Get rid of assumptions here...
        if (command.split(" ").length != 2) {
            System.out.print("ERROR: Invalid `return` format:\n\targs: ");
            for (String token : command.split(" "))
                System.out.print(String.format("%s", token));
            System.out.println();
            return;
        }

        // Perform
        String isbn = command.split(" ")[1];

        int oldSize = loans.size();
        loans.removeIf(l -> l.isbn.equals(isbn));
        if (loans.size() != oldSize)
            System.out.println("Returned book successfully.");
        else
            System.out.println("ERROR: Book did not exist!");
    }

    public void ListBooks(String command) {
        books.forEach(b -> {
            System.out.println(String.format("%10s %10s %10s", b.isbn, b.title, b.author));
        });
    }

    public void ListLoans(String command) {
        loans.forEach(l -> {
            System.out.println(String.format("%10s %10s %10s", l.isbn, l.memberId, l.dueDate.toString()));
        });
    }

    public void FindBook(String command) {

        // Validate
        // TODO: Get rid of assumptions here...
        if (command.split(" ").length != 2) {
            System.out.print("ERROR: Invalid `return` format:\n\targs: ");
            for (String token : command.split(" "))
                System.out.print(String.format("%s", token));
            System.out.println();
            return;
        }

        String key = command.split(" ")[1];
        for (Book b : books) {
            if (
                b.title.equals(key)
                || b.author.equals(key)
                || b.isbn.equals(key)
            ) {
                System.out.println(b.toString());
                return;
            }
        }

        System.out.println("Book not found...");
    }

    public void Exit(String command) {
        System.out.println("LIB MGR DONE");
        System.exit(0);
    }

    public void Persist(String command) {
        System.out.println("[books]");
        for (Book b : books) System.out.println(b.toString());
        System.out.println();

        System.out.println("[members]");
        for (Member m : members) System.out.println(m.toString());
        System.out.println();

        System.out.println("[loans]");
        for (Loan l : loans) System.out.println(l.toString());
        System.out.println();

        // Config-file style store
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("database.txt"))) {
            writer.write("[books]"); writer.newLine();
            for (Book b : books) writer.write(b.toString());
            writer.newLine();

            writer.write("[members]"); writer.newLine();
            for (Member m : members) writer.write(m.toString());
            writer.newLine();

            writer.write("[loans]"); writer.newLine();
            for (Loan l : loans) writer.write(l.toString());
            writer.newLine();
        } catch (IOException e) {
            // Do nothing...
        }
    }

    public String GetNextCommand() {

        System.out.println("------------------------------");
        System.out.println("Available commands:");
        COMMANDS.forEach((k, v) -> { System.out.println(String.format("\t- %s", k)); });
        System.out.println("------------------------------");
        System.out.print("LIB MGR >>> ");

        while (true) {
            String cmd = in.nextLine().toLowerCase().trim();
            if (COMMANDS.containsKey(cmd.split(" ")[0])) return cmd;
            System.out.println(String.format("Unrecognized command: '%s'", cmd.split(" ")[0]));
            System.out.print("LIB MGR >>> ");
        }
    }

    public void MainRunLoop() {
        while (true) {
            String cmd = GetNextCommand();
            COMMANDS.get(cmd.split(" ")[0]).accept(cmd);
        }
    }

    public Main() {
        in = new Scanner(System.in);

        // TODO: Initialize statically?
        {
            HashMap<String, Consumer<String>> commands = new HashMap<>();
            commands.put("add-book", this::AddBook);
            commands.put("add-member", this::AddMember);
            commands.put("loan", this::Loan);
            commands.put("return", this::Return);
            commands.put("list-books", this::ListBooks);
            commands.put("list-loans", this::ListLoans);
            commands.put("find-book", this::FindBook);
            commands.put("exit", this::Exit);
            commands.put("persist", this::Persist);
            COMMANDS = commands;
        }
    }

}
