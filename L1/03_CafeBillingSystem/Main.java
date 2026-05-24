import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Main {

    public static final Map<String, Float> MENU;

    static {
        Map<String, Float> menu = new HashMap<>();
        menu.put("coffee", 4.50f);
        menu.put("tea", 3.50f);
        menu.put("sandwich", 8.50f);
        menu.put("cookie", 4.25f);
        MENU = menu;
    }

    public static void main(String[] args) { new Main(); }

    public void PrintMenu() {
        System.out.println("----- MENU -----");
        MENU.forEach((item, price) -> {
            System.out.println(String.format("%10s: $%.2f", item, price));
        });
        System.out.println("----------------");
    }

    class InvalidItemException extends Exception { public InvalidItemException(String msg) { super(msg); } }

    public ArrayList<String> GetCustomerOrder() {

        Scanner in = new Scanner(System.in);
        ArrayList<String> order = new ArrayList<String>();

        while (true) {
            try {
                System.out.print("Order item (type 'done' to finish order): ");

                String choice = in.next().toLowerCase();

                if (choice.equals("done")) break;
                if (!MENU.containsKey(choice)) throw new InvalidItemException("Invalid Item Chosen!");

                System.out.println(String.format("Added: %s ($%.2f)", choice, MENU.get(choice)));
                order.add(choice);

            } catch (InvalidItemException e) {
                System.out.println("Oops! Something went wrong...");
            }
        }

        return order;
    }

    public void PrintCustomerOrder(ArrayList<String> order) {

        float total = 0.0f;

        System.out.println("----- RECEIPT -----");
        for (int i = 0; i < order.size(); i++) {
            String item = order.get(i);
            System.out.println(String.format("%10s: $%.2f", item, MENU.get(item)));
            total += MENU.get(item);
        }
        System.out.println("-------------------");
        System.out.println(String.format("  subtotal: $%.2f", total));
        System.out.println(String.format("tax(8.25%%): $%.2f", total * 0.0825f));
        System.out.println("-------------------");
        System.out.println(String.format("     total: $%.2f", total * 1.0825));
        System.out.println("-------------------");
    }

    public Main() {

        PrintMenu();

        ArrayList<String> order = GetCustomerOrder();

        PrintCustomerOrder(order);

    }

}
