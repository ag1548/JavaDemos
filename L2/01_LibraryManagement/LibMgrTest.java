import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibMgrTest {

    @Test
    void AddBook() {
        Main main = new Main();
        main.AddBook("add-book 9780134685991 Effective_Java Bloch");

        assertTrue(main.books.contains(main.new Book("9780134685991", "Effective_Java", "Bloch")));
    }

    @Test
    void AddMember() {
        Main main = new Main();
        main.AddMember("add-member M1 Alice");

        assertTrue(main.members.contains(main.new Member("M1", "Alice")));
    }

    @Test
    void AddLoan() {
        Main main = new Main();
        main.Loan("loan 9780134685991 M1 2026-06-01");

        String testDate = "2026-06-01";

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = df.parse("2026-06-01");
            assertTrue(main.loans.contains(main.new Loan("9780134685991", "M1", dueDate)));
        } catch (ParseException e) {
        }
    }

}