package untar.java.tugas1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryApp extends JFrame {
    private JTextField codeField, nameField, qtyField, priceField;
    private JButton addButton, deleteButton;
    private JTable productTable;
    private ProductTableModel tableModel;

    public InventoryApp() {
        super ("Inventory App (Memory Only)");

        tableModel = new ProductTableModel ();
        productTable = new JTable (tableModel);

        JPanel inputPanel = new JPanel (new GridLayout (5, 2, 5, 5));
        inputPanel.add (new JLabel ("Kode:"));
        codeField = new JTextField ();
        inputPanel.add (codeField);

        inputPanel.add (new JLabel ("Nama:"));
        nameField = new JTextField ();
        inputPanel.add (nameField);

        inputPanel.add (new JLabel ("Qty:"));
        qtyField = new JTextField ();
        inputPanel.add (qtyField);

        inputPanel.add (new JLabel ("Harga:"));
        priceField = new JTextField ();
        inputPanel.add (priceField);

        addButton = new JButton ("Tambah");
        inputPanel.add (new JLabel ());
        inputPanel.add (addButton);

        JScrollPane tableScroll = new JScrollPane (productTable);

        deleteButton = new JButton ("Hapus Produk Terpilih");

        setLayout (new BorderLayout (10, 10));
        add (inputPanel, BorderLayout.NORTH);
        add (tableScroll, BorderLayout.CENTER);
        add (deleteButton, BorderLayout.SOUTH);

        addButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    String code = codeField.getText ();
                    String name = nameField.getText ();
                    int qty = Integer.parseInt(qtyField.getText ());
                    double price = Double.parseDouble(priceField.getText ());

                    ProductModel product = new ProductModel (code, name, qty, price);
                    tableModel.addProduct (product);

                    codeField.setText ("");
                    nameField.setText ("");
                    qtyField.setText ("");
                    priceField.setText ("");
                } 
                
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog (
                        InventoryApp.this,
                        "Qty dan Harga harus berupa angka!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        deleteButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                int selectedRow = productTable.getSelectedRow ();
                if (selectedRow >= 0) {
                    tableModel.removeProduct (selectedRow);
                }

                else {
                    JOptionPane.showMessageDialog(
                        InventoryApp.this,
                        "Pilih produk yang ingin dihapus!",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setSize (600, 400);
        setLocationRelativeTo (null);
        setVisible (true);
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater ( () -> new InventoryApp ());
    }
}
