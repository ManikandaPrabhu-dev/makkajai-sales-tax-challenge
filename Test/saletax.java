import java.util.List;
import java.util.ArrayList;

class Item {
    String name;
    double price;
    boolean isImported;
    boolean isExempt;

    public Item(String name, double price, boolean isImported, boolean isExempt) {
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.isExempt = isExempt;
    }
}

class TaxCalculator {
    public double calculateTax(Item item) {
        double taxRate = 0.0;
        if (!item.isExempt) {
            taxRate += 0.10; // basic sales tax
        }
        if (item.isImported) {
            taxRate += 0.05; // import duty
        }

        double rawTax = item.price * taxRate;
        return roundTax(rawTax);
    }

    public double roundTax(double tax) {
        return Math.ceil(tax * 20.0) / 20.0; // round up to nearest 0.05
    }
}

class Receipt {
    List<Item> items;
    TaxCalculator calculator;

    public Receipt(List<Item> items, TaxCalculator calculator) {
        this.items = items;
        this.calculator = calculator;
    }

    public void printReceipt() {
        double total = 0;
        double taxTotal = 0;
        for (Item item : items) {
            double tax = calculator.calculateTax(item);
            double totalPrice = item.price + tax;
            System.out.println("1 " + item.name + ": " + String.format("%.2f", totalPrice));
            total += totalPrice;
            taxTotal += tax;
        }
        System.out.println("Sales Taxes: " + String.format("%.2f", taxTotal));
        System.out.println("Total: " + String.format("%.2f", total));
    }
}

public class SalesTaxApp {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("book", 12.49, false, true));
        items.add(new Item("music CD", 14.99, false, false));
        items.add(new Item("chocolate bar", 0.85, false, true));

        Receipt receipt = new Receipt(items, new TaxCalculator());
        receipt.printReceipt();
    }
}