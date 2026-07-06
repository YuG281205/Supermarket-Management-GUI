/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JButton1;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class InvoicePrinter extends JFrame implements Printable {
    JPanel invoicePanel;

    public InvoicePrinter() {
        setTitle("Invoice Example");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the invoice panel
        invoicePanel = new JPanel();
        invoicePanel.setLayout(new BoxLayout(invoicePanel, BoxLayout.Y_AXIS));
        invoicePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add invoice content
        invoicePanel.add(new JLabel("Supermarket Invoice"));
        invoicePanel.add(new JLabel("Customer Name: John Doe"));
        invoicePanel.add(new JLabel("Date: 2025-06-12"));
        invoicePanel.add(new JLabel("Item 1: Milk - ₹50"));
        invoicePanel.add(new JLabel("Item 2: Bread - ₹40"));
        invoicePanel.add(new JLabel("Total: ₹90"));

        // Add Print Button
        JButton printBtn = new JButton("Print Invoice");
        printBtn.addActionListener(e -> printInvoice());

        add(invoicePanel, BorderLayout.CENTER);
        add(printBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void printInvoice() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Invoice");

        job.setPrintable(this);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Print Error: " + ex.getMessage());
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Translate the panel to fit the printable area
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());

        // Scale to fit the page width
        double scaleX = pf.getImageableWidth() / invoicePanel.getWidth();
        double scaleY = pf.getImageableHeight() / invoicePanel.getHeight();
        double scale = Math.min(scaleX, scaleY);
        g2.scale(scale, scale);

        invoicePanel.printAll(g);

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        new InvoicePrinter();
    }
}