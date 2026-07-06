package JButton1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// ──────────────────────────────────────────────
//  Bill Window – one per customer
// ──────────────────────────────────────────────
class TotalBillWindow extends JFrame implements Printable {

    private JPanel billPanel;

    TotalBillWindow(int customerId, String customerName, long contactNo, int queueNo,
                    java.util.List<String[]> items, double grandTotal) {

        setTitle("Total Bill - " + customerName + " (ID: " + customerId + ")");
        setSize(500, 680);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon("yug.jpg").getImage());

        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(new Color(0x00243e));
        outer.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // header
        JPanel header = new JPanel();
        header.setBackground(new Color(0x00243e));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(18, 20, 10, 20));

        JLabel shopName = new JLabel("GHUMA SUPERMARKET");
        shopName.setFont(new Font("Times New Roman", Font.BOLD, 22));
        shopName.setForeground(Color.WHITE);
        shopName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel address = new JLabel("Ghuma BRTS Road | Ph: 8401331819");
        address.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        address.setForeground(new Color(0xCCCCCC));
        address.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep1 = new JSeparator();
        sep1.setForeground(new Color(0x4488aa));
        sep1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));

        String dateStr = new SimpleDateFormat("dd-MM-yyyy  HH:mm").format(new Date());
        JLabel dateLabel = new JLabel("Date: " + dateStr);
        dateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(0xCCCCCC));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel custLabel = new JLabel("Customer ID: " + customerId + "    Name: " + customerName);
        custLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        custLabel.setForeground(Color.WHITE);
        custLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contactLabel = new JLabel("Contact: " + contactNo +
            (queueNo > 0 ? "    Queue No: " + queueNo : "    Queue No: -"));
        contactLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        contactLabel.setForeground(new Color(0xCCCCCC));
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel billTitle = new JLabel("-- TOTAL BILL --");
        billTitle.setFont(new Font("Times New Roman", Font.BOLD, 15));
        billTitle.setForeground(new Color(0xFFCC00));
        billTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(shopName);
        header.add(Box.createVerticalStrut(4));
        header.add(address);
        header.add(Box.createVerticalStrut(8));
        header.add(sep1);
        header.add(Box.createVerticalStrut(6));
        header.add(dateLabel);
        header.add(Box.createVerticalStrut(4));
        header.add(custLabel);
        header.add(Box.createVerticalStrut(2));
        header.add(contactLabel);
        header.add(Box.createVerticalStrut(6));
        header.add(billTitle);
        header.add(Box.createVerticalStrut(6));

        // table
        String[] cols = {"Order ID", "Product", "Qty", "Unit Price", "Amount"};
        Object[][] data = new Object[items.size()][5];
        for (int i = 0; i < items.size(); i++) data[i] = items.get(i);

        JTable table = new JTable(data, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table.setBackground(new Color(0x0a2d4a));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        table.setRowHeight(26);
        table.getTableHeader().setBackground(new Color(0x00243e));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 13));
        table.setGridColor(new Color(0x4488aa));
        table.setSelectionBackground(new Color(0x1a5276));
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);

        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(new Color(0x0a2d4a));
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(0x00243e));
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(BorderFactory.createEmptyBorder(8, 20, 10, 20));

        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(0x4488aa));
        sep2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));

        JLabel itemCountLabel = new JLabel("Total Items: " + items.size());
        itemCountLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        itemCountLabel.setForeground(new Color(0xCCCCCC));
        itemCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel totalLabel = new JLabel(String.format("GRAND TOTAL :  Rs. %.2f", grandTotal));
        totalLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        totalLabel.setForeground(new Color(0x00ff99));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel thanks = new JLabel("Thank you for shopping with us!");
        thanks.setFont(new Font("Times New Roman", Font.ITALIC, 13));
        thanks.setForeground(new Color(0xCCCCCC));
        thanks.setAlignmentX(Component.CENTER_ALIGNMENT);

        footer.add(sep2);
        footer.add(Box.createVerticalStrut(6));
        footer.add(itemCountLabel);
        footer.add(Box.createVerticalStrut(4));
        footer.add(totalLabel);
        footer.add(Box.createVerticalStrut(6));
        footer.add(thanks);

        // buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        btnPanel.setBackground(new Color(0x00243e));

        JButton printBtn = new JButton("Print Bill");
        printBtn.setBackground(new Color(0xCCCCCC));
        printBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
        printBtn.setFocusable(false);
        printBtn.setBorder(BorderFactory.createEtchedBorder());
        printBtn.addActionListener(ev -> printBill());

        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(0xCCCCCC));
        closeBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
        closeBtn.setFocusable(false);
        closeBtn.setBorder(BorderFactory.createEtchedBorder());
        closeBtn.addActionListener(ev -> dispose());

        btnPanel.add(printBtn);
        btnPanel.add(closeBtn);

        billPanel = new JPanel(new BorderLayout());
        billPanel.setBackground(new Color(0x00243e));
        billPanel.add(header, BorderLayout.NORTH);
        billPanel.add(scroll, BorderLayout.CENTER);
        billPanel.add(footer, BorderLayout.SOUTH);

        outer.add(billPanel, BorderLayout.CENTER);
        outer.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(outer);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void printBill() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Total Bill");
        job.setPrintable(this);
        if (job.printDialog()) {
            try { job.print(); }
            catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Print Error: " + ex.getMessage());
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) return NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        double scale = Math.min(pf.getImageableWidth() / billPanel.getWidth(),
                                pf.getImageableHeight() / billPanel.getHeight());
        g2.scale(scale, scale);
        billPanel.printAll(g);
        return PAGE_EXISTS;
    }
}

// ──────────────────────────────────────────────
//  BillingPanel – renamed to avoid case conflict
// ──────────────────────────────────────────────
class BillingPanel implements ActionListener {

    private static final String URL      = "jdbc:mysql://localhost:3306/?user=root";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "YuG@#2647";

    JFrame    frame          = new JFrame();
    JPanel    headerPanel    = new JPanel();
    JLabel    headerLabel    = new JLabel();
    JTextArea infoArea       = new JTextArea();
    JButton   nextCustBtn    = new JButton();
    JButton   billAllBtn     = new JButton();
    JButton   resetBtn       = new JButton();
    JLabel    custStatus     = new JLabel("Current Customer: -");
    JLabel    totalCustLabel = new JLabel("Total Customers: loading...");
    JLabel    grandSumLabel  = new JLabel("All Bills Total: Rs. 0.00");

    private int currentCustIndex = 0;
    private java.util.List<Integer> customerIds   = new java.util.ArrayList<>();
    private java.util.List<String>  customerNames = new java.util.ArrayList<>();

    BillingPanel() {

        // header with centered image
        headerPanel.setBackground(new Color(0x00243e));
        headerPanel.setBounds(0, 0, 960, 200);
        headerPanel.setLayout(new BorderLayout());
        headerLabel.setIcon(new ImageIcon("queue_billing.jpg"));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // column header
        JPanel infoHeaderPanel = new JPanel();
        infoHeaderPanel.setBackground(new Color(0x00243e));
        infoHeaderPanel.setBounds(0, 210, 960, 30);
        JLabel colLabel = new JLabel(
            "   Cust ID    Customer Name          Contact No          Queue No    Total Orders    Grand Total");
        colLabel.setForeground(Color.WHITE);
        colLabel.setFont(new Font("Courier New", Font.BOLD, 12));
        infoHeaderPanel.setLayout(new BorderLayout());
        infoHeaderPanel.add(colLabel);

        // info area
        infoArea.setBackground(new Color(0x0a2d4a));
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        infoArea.setEditable(false);
        infoArea.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(0, 240, 960, 230);
        scrollPane.getViewport().setBackground(new Color(0x0a2d4a));

        // status labels
        custStatus.setBounds(20, 490, 460, 30);
        custStatus.setFont(new Font("Times New Roman", Font.BOLD, 14));
        custStatus.setForeground(new Color(0x00ff99));

        totalCustLabel.setBounds(490, 490, 450, 30);
        totalCustLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        totalCustLabel.setForeground(Color.WHITE);

        grandSumLabel.setBounds(20, 528, 920, 30);
        grandSumLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        grandSumLabel.setForeground(new Color(0xFFCC00));

        // buttons
        nextCustBtn.setBounds(20, 570, 300, 65);
        nextCustBtn.setBackground(new Color(0x00243e));
        nextCustBtn.setText("Next Customer Bill ->");
        nextCustBtn.setForeground(Color.WHITE);
        nextCustBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
        nextCustBtn.setFocusable(false);
        nextCustBtn.setBorder(BorderFactory.createEtchedBorder());
        nextCustBtn.addActionListener(this);

        billAllBtn.setBounds(340, 570, 300, 65);
        billAllBtn.setBackground(new Color(0x00243e));
        billAllBtn.setText("Bill ALL Customers");
        billAllBtn.setForeground(Color.WHITE);
        billAllBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
        billAllBtn.setFocusable(false);
        billAllBtn.setBorder(BorderFactory.createEtchedBorder());
        billAllBtn.addActionListener(this);

        resetBtn.setBounds(660, 570, 280, 65);
        resetBtn.setBackground(new Color(0xCCCCCC));
        resetBtn.setText("Reset Counter");
        resetBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
        resetBtn.setFocusable(false);
        resetBtn.setBorder(BorderFactory.createEtchedBorder());
        resetBtn.addActionListener(this);

        // frame
        frame.setIconImage(new ImageIcon("yug.jpg").getImage());
        frame.setTitle("Total Billing Panel ->");
        frame.setSize(980, 710);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.add(headerPanel);
        frame.add(infoHeaderPanel);
        frame.add(scrollPane);
        frame.add(custStatus);
        frame.add(totalCustLabel);
        frame.add(grandSumLabel);
        frame.add(nextCustBtn);
        frame.add(billAllBtn);
        frame.add(resetBtn);
        frame.setVisible(true);

        loadCustomers();
    }

    private void loadCustomers() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt  = conn.createStatement()) {

            stmt.executeUpdate("use supermarket");

            String sql =
                "SELECT c.c_id, c.c_name, c.c_no, c.queue_no, " +
                "COUNT(o.o_id) AS order_count, " +
                "SUM(o.o_quantity * p.p_price) AS total_amount " +
                "FROM customer c " +
                "JOIN orders o ON c.c_id = o.c_id " +
                "JOIN product p ON o.p_id = p.p_id " +
                "GROUP BY c.c_id, c.c_name, c.c_no, c.queue_no " +
                "ORDER BY c.c_id ASC";

            ResultSet rs = stmt.executeQuery(sql);

            customerIds.clear();
            customerNames.clear();
            StringBuilder sb = new StringBuilder();
            double allTotal  = 0;

            while (rs.next()) {
                int    cid     = rs.getInt("c_id");
                String cname   = rs.getString("c_name");
                long   cno     = rs.getLong("c_no");
                int    qno     = rs.getInt("queue_no");
                int    ocount  = rs.getInt("order_count");
                double camount = rs.getDouble("total_amount");
                allTotal += camount;

                customerIds.add(cid);
                customerNames.add(cname);

                sb.append(String.format(
                    "   %-10d  %-22s  %-20d  %-10d  %-14d  Rs.%.2f%n",
                    cid, cname, cno, qno, ocount, camount));
            }

            infoArea.setText(sb.length() > 0
                ? sb.toString()
                : "  No customers with orders found in database.");
            totalCustLabel.setText("Total Customers: " + customerIds.size());
            grandSumLabel.setText(String.format(
                "All Bills Combined Total:  Rs. %.2f", allTotal));

        } catch (Exception e) {
            infoArea.setText("DB Error: " + e.getMessage());
            JOptionPane.showMessageDialog(frame, "DB Error: " + e.getMessage());
        }
    }

    private void generateBill(int customerId) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt  = conn.createStatement()) {

            stmt.executeUpdate("use supermarket");

            ResultSet crs = stmt.executeQuery(
                "SELECT c_id, c_name, c_no, queue_no FROM customer WHERE c_id = " + customerId);

            if (!crs.next()) {
                JOptionPane.showMessageDialog(frame, "No customer found with ID: " + customerId);
                return;
            }
            String cName = crs.getString("c_name");
            long   cNo   = crs.getLong("c_no");
            int    qNo   = crs.getInt("queue_no");

            ResultSet ors = stmt.executeQuery(
                "SELECT o.o_id, p.p_name, o.o_quantity, p.p_price " +
                "FROM orders o " +
                "JOIN product p ON o.p_id = p.p_id " +
                "WHERE o.c_id = " + customerId + " ORDER BY o.o_id ASC");

            java.util.List<String[]> items = new java.util.ArrayList<>();
            double grandTotal = 0;

            while (ors.next()) {
                int    oId    = ors.getInt("o_id");
                String pName  = ors.getString("p_name");
                int    qty    = ors.getInt("o_quantity");
                double price  = ors.getDouble("p_price");
                double amount = qty * price;
                grandTotal   += amount;

                items.add(new String[]{
                    String.valueOf(oId), pName, String.valueOf(qty),
                    String.format("Rs.%.2f", price),
                    String.format("Rs.%.2f", amount)
                });
            }

            if (items.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Customer " + cName + " has no orders.");
                return;
            }

            new TotalBillWindow(customerId, cName, cNo, qNo, items, grandTotal);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nextCustBtn) {
            if (customerIds.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No customers with orders found!");
                return;
            }
            if (currentCustIndex >= customerIds.size()) {
                JOptionPane.showMessageDialog(frame,
                    "All " + customerIds.size() +
                    " customers billed!\nClick 'Reset Counter' to restart.");
                return;
            }
            int cId = customerIds.get(currentCustIndex);
            generateBill(cId);
            custStatus.setText("Billed: " + customerNames.get(currentCustIndex) +
                " (" + (currentCustIndex + 1) + " of " + customerIds.size() + ")");
            currentCustIndex++;
        }

        if (e.getSource() == billAllBtn) {
            if (customerIds.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No customers with orders found!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(frame,
                "This will open " + customerIds.size() + " bill windows.\nContinue?",
                "Bill All Customers", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                for (int cId : customerIds) generateBill(cId);
                custStatus.setText("All " + customerIds.size() + " bills generated.");
                currentCustIndex = customerIds.size();
            }
        }

        if (e.getSource() == resetBtn) {
            currentCustIndex = 0;
            custStatus.setText("Current Customer: -");
            loadCustomers();
            JOptionPane.showMessageDialog(frame, "Counter reset. Ready to bill from Customer #1.");
        }
    }
}

// ──────────────────────────────────────────────
//  Public main class — filename: Total_billing.java
// ──────────────────────────────────────────────
public class Total_billing {
    public static void main(String[] args) {
        new BillingPanel();
    }
}