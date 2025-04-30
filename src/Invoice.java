import java.util.ArrayList;

public class Invoice {
    private String title;
    private CustomerAddress customerAddress;
    private ArrayList<LineItem> lineItems;

    public Invoice() {
        lineItems = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice Title: ").append(title).append("\n");
        sb.append("Customer:\n").append(customerAddress).append("\n");
        sb.append("Line Items:\n");

        double totalAmount = 0;
        for (LineItem item : lineItems) {
            sb.append(item).append("\n");
            totalAmount += item.calculateTotal();
        }

        sb.append("\nTotal Amount Due: $").append(String.format("%.2f", totalAmount));
        return sb.toString();
    }
}