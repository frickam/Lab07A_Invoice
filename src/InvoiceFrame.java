import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {
    private JTextField titleField;
    private JTextField customerNameField;
    private JTextField customerAddressField;
    private JTextField productNameField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextArea displayArea;

    private Invoice invoice;

    public InvoiceFrame() {
        setTitle("Invoice Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        invoice = new Invoice();

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));

        // Invoice Title
        inputPanel.add(new JLabel("Invoice Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        // Customer Name
        inputPanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField();
        inputPanel.add(customerNameField);

        // Customer Address
        inputPanel.add(new JLabel("Customer Address:"));
        customerAddressField = new JTextField();
        inputPanel.add(customerAddressField);

        // Product Name
        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);

        // Unit Price
        inputPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);

        // Quantity
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        // Add Input Panel to the Frame
        add(inputPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addLineItemButton = new JButton("Add Line Item");
        JButton generateInvoiceButton = new JButton("Generate Invoice");

        buttonPanel.add(addLineItemButton);
        buttonPanel.add(generateInvoiceButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // Button Listeners
        addLineItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLineItem();
            }
        });

        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice();
            }
        });
    }

    private void addLineItem() {
        try {
            String productName = productNameField.getText();
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Product product = new Product(productName, unitPrice);
            LineItem lineItem = new LineItem(product, quantity);

            invoice.addLineItem(lineItem);

            JOptionPane.showMessageDialog(this, "Line item added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for Unit Price and Quantity.");
        }
    }

    private void generateInvoice() {
        try {
            String title = titleField.getText();
            String customerName = customerNameField.getText();
            String customerAddress = customerAddressField.getText();

            invoice.setTitle(title);
            invoice.setCustomerAddress(new CustomerAddress(customerName, customerAddress));

            displayArea.setText(invoice.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating invoice. Please check your input.");
        }
    }
}