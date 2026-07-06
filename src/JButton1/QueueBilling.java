/*
 * Queue Billing - Bills customers queue by queue (queue_no > 0)
 * Each queue gets a separate bill window with all their orders
 * Header image is centered
 */
package JButton1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// ──────────────────────────────────────────────
//  Bill Window – one per queue
// ──────────────────────────────────────────────
class BillWindow extends JFrame implements Printable {

    private JPanel billPanel;

    BillWindow(int queueNo, String customerName, long contactNo,
               java.util.List<String[]> items, double grandTotal) {

        setTitle("Bill - Queue #" + queueNo + " | " + customerName);
        setSize(480, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        ImageIcon icon = new ImageIcon("yug.jpg");
        setIconImage(icon.getImage());

        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(new Color(0x00243e));
        outer.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // ── header ──
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

        JLabel queueLabel = new JLabel("Queue No: " + queueNo + "    Customer: " + customerName);
        queueLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        queueLabel.setForeground(Color.WHITE);
        queueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contactLabel = new JLabel("Contact: " + contactNo);
        contactLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        contactLabel.setForeground(new Color(0xCCCCCC));
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(shopName);
        header.add(Box.createVerticalStrut(4));
        header.add(address);
        header.add(Box.createVerticalStrut(8));
        header.add(sep1);
        header.add(Box.createVerticalStrut(6));
        header.add(dateLabel);
        header.add(Box.createVerticalStrut(4));
        header.add(queueLabel);
        header.add(Box.createVerticalStrut(2));
        header.add(contactLabel);
        header.add(Box.createVerticalStrut(8));

        // ── items table ──
        String[] cols = {"Order ID", "Product", "Qty", "Price", "Amount"};
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
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);

        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(new Color(0x0a2d4a));
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // ── footer ──
        JPanel footer = new JPanel();
        footer.setBackground(new Color(0x00243e));
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(BorderFactory.createEmptyBorder(8, 20, 10, 20));

        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(0x4488aa));
        sep2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));

        JLabel totalLabel = new JLabel(String.format("GRAND TOTAL :  Rs. %.2f", grandTotal));
        totalLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        totalLabel.setForeground(new Color(0x00ff99));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel thanks = new JLabel("Thank you for shopping with us!");
        thanks.setFont(new Font("Times New Roman", Font.ITALIC, 13));
        thanks.setForeground(new Color(0xCCCCCC));
        thanks.setAlignmentX(Component.CENTER_ALIGNMENT);

        footer.add(sep2);
        footer.add(Box.createVerticalStrut(6));
        footer.add(totalLabel);
        footer.add(Box.createVerticalStrut(6));
        footer.add(thanks);

        // ── print / close buttons ──
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
        job.setJobName("Queue Bill");
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
//  Queue_Billing – main panel
// ──────────────────────────────────────────────
class Queue_Billing implements ActionListener {

    private static final String URL      = "jdbc:mysql://localhost:3306/?user=root";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "YuG@#2647";

    JFrame    frame        = new JFrame();
    ImageIcon icon         = new ImageIcon("yug.jpg");
    ImageIcon icon1        = new ImageIcon("queue_billing.jpg");

    JPanel    headerPanel  = new JPanel();
    JLabel    headerLabel  = new JLabel();          // <-- image label
    JTextArea infoArea     = new JTextArea();
    JButton   nextQueueBtn = new JButton();
    JButton   resetBtn     = new JButton();
    JLabel    queueStatus  = new JLabel("Current Queue: -");
    JLabel    totalQueues  = new JLabel("Queues with orders: loading...");

    private int currentQueueIndex = 0;
    private java.util.List<Integer> queueNumbers = new java.util.ArrayList<>();

    Queue_Billing() {

        // ── header panel with CENTERED image ──
        headerPanel.setBackground(new Color(0x00243e));
        headerPanel.setBounds(0, 0, 940, 200);
        headerPanel.setLayout(new BorderLayout());          // BorderLayout needed
        headerLabel.setIcon(icon1);
        headerLabel.setHorizontalAlignment(JLabel.CENTER); // center horizontally
        headerLabel.setVerticalAlignment(JLabel.CENTER);   // center vertically
        headerPanel.add(headerLabel, BorderLayout.CENTER); // add to CENTER

        // ── column header row ──
        JPanel infoHeaderPanel = new JPanel();
        infoHeaderPanel.setBackground(new Color(0x00243e));
        infoHeaderPanel.setBounds(0, 210, 940, 30);
        JLabel colLabel = new JLabel(
            "   Queue#    Cust ID    Customer Name          Contact No        Order Count");
        colLabel.setForeground(Color.WHITE);
        colLabel.setFont(new Font("Courier New", Font.BOLD, 13));
        infoHeaderPanel.setLayout(new BorderLayout());
        infoHeaderPanel.add(colLabel);

        // ── info text area ──
        infoArea.setBackground(new Color(0x0a2d4a));
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        infoArea.setEditable(false);
        infoArea.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(0, 240, 940, 250);
        scrollPane.getViewport().setBackground(new Color(0x0a2d4a));

        // ── status labels ──
        queueStatus.setBounds(20, 510, 460, 35);
        queueStatus.setFont(new Font("Times New Roman", Font.BOLD, 15));
        queueStatus.setForeground(new Color(0x00ff99));

        totalQueues.setBounds(490, 510, 430, 35);
        totalQueues.setFont(new Font("Times New Roman", Font.BOLD, 14));
        totalQueues.setForeground(Color.WHITE);

        // ── next queue button ──
        nextQueueBtn.setBounds(20, 560, 450, 70);
        nextQueueBtn.setBackground(new Color(0x00243e));
        nextQueueBtn.setText("Click here for Next Queue Bill ->");
        nextQueueBtn.setForeground(Color.WHITE);
        nextQueueBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nextQueueBtn.setFocusable(false);
        nextQueueBtn.setBorder(BorderFactory.createEtchedBorder());
        nextQueueBtn.addActionListener(this);

        // ── reset button ──
        resetBtn.setBounds(490, 560, 430, 70);
        resetBtn.setBackground(new Color(0xCCCCCC));
        resetBtn.setText("Reset Queue Counter");
        resetBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        resetBtn.setFocusable(false);
        resetBtn.setBorder(BorderFactory.createEtchedBorder());
        resetBtn.addActionListener(this);

        // ── frame ──
        frame.setIconImage(icon.getImage());
        frame.setTitle("Queue Billing Panel ->");
        frame.setSize(960, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        frame.add(headerPanel);
        frame.add(infoHeaderPanel);
        frame.add(scrollPane);
        frame.add(queueStatus);
        frame.add(totalQueues);
        frame.add(nextQueueBtn);
        frame.add(resetBtn);
        frame.setVisible(true);

        loadQueues();
    }

    // ── load all queue_no > 0 from DB ──
    private void loadQueues() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt  = conn.createStatement()) {

            stmt.executeUpdate("use supermarket");

            String sql =
                "SELECT c.queue_no, c.c_id, c.c_name, c.c_no, COUNT(o.o_id) AS order_count " +
                "FROM customer c " +
                "LEFT JOIN orders o ON c.c_id = o.c_id " +
                "WHERE c.queue_no > 0 " +
                "GROUP BY c.queue_no, c.c_id, c.c_name, c.c_no " +
                "ORDER BY c.queue_no ASC";

            ResultSet rs = stmt.executeQuery(sql);

            queueNumbers.clear();
            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                int    q      = rs.getInt("queue_no");
                int    cid    = rs.getInt("c_id");
                String cname  = rs.getString("c_name");
                long   cno    = rs.getLong("c_no");
                int    ocount = rs.getInt("order_count");

                if (!queueNumbers.contains(q)) queueNumbers.add(q);

                sb.append(String.format(
                    "   %-8d  %-10d  %-22s  %-18d  %d order(s)%n",
                    q, cid, cname, cno, ocount));
            }

            infoArea.setText(sb.length() > 0
                ? sb.toString()
                : "  No customers with queue_no > 0 found in database.");
            totalQueues.setText("Total Queues: " + queueNumbers.size());

        } catch (Exception e) {
            infoArea.setText("DB Error: " + e.getMessage());
        }
    }

    // ── generate & show bill for one queue ──
    private void generateBill(int queueNo) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt  = conn.createStatement()) {

            stmt.executeUpdate("use supermarket");

            ResultSet crs = stmt.executeQuery(
                "SELECT c_id, c_name, c_no FROM customer WHERE queue_no = " + queueNo);

            if (!crs.next()) {
                JOptionPane.showMessageDialog(frame,
                    "No customer found for Queue #" + queueNo);
                return;
            }
            int    cId   = crs.getInt("c_id");
            String cName = crs.getString("c_name");
            long   cNo   = crs.getLong("c_no");

            ResultSet ors = stmt.executeQuery(
                "SELECT o.o_id, p.p_name, o.o_quantity, p.p_price " +
                "FROM orders o " +
                "JOIN product p ON o.p_id = p.p_id " +
                "WHERE o.c_id = " + cId);

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
                JOptionPane.showMessageDialog(frame,
                    "Queue #" + queueNo + " (" + cName + ") has no orders yet.");
                return;
            }

            new BillWindow(queueNo, cName, cNo, items, grandTotal);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextQueueBtn) {
            if (queueNumbers.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                    "No queues with queue_no > 0 found!");
                return;
            }
            if (currentQueueIndex >= queueNumbers.size()) {
                JOptionPane.showMessageDialog(frame,
                    "All " + queueNumbers.size() +
                    " queues have been billed!\nClick 'Reset Queue Counter' to restart.");
                return;
            }
            int qNo = queueNumbers.get(currentQueueIndex);
            generateBill(qNo);
            queueStatus.setText("Billed Queue: #" + qNo +
                " (" + (currentQueueIndex + 1) + " of " + queueNumbers.size() + ")");
            currentQueueIndex++;
        }

        if (e.getSource() == resetBtn) {
            currentQueueIndex = 0;
            queueStatus.setText("Current Queue: -");
            loadQueues();
            JOptionPane.showMessageDialog(frame,
                "Queue counter reset. Ready to bill from Queue #1.");
        }
    }
}

// ──────────────────────────────────────────────
//  Public main class
// ──────────────────────────────────────────────
public class QueueBilling {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Queue_Billing::new);
    }
}
