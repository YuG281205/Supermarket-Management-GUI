package JButton1;

import jakarta.persistence.*;

/**
 * Entity mapped to the "customer" table.
 *
 * Table schema (matches original JDBC code):
 *   customer(c_id INT PK, c_name VARCHAR, c_no BIGINT, queue_no INT)
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "c_id")
    private int id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_no")
    private long contactNumber;

    @Column(name = "queue_no")
    private int queueNumber;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Customer() {}

    public Customer(int id, String name, long contactNumber, int queueNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.queueNumber = queueNumber;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public int getId()                     { return id; }
    public void setId(int id)              { this.id = id; }

    public String getName()                { return name; }
    public void setName(String name)       { this.name = name; }

    public long getContactNumber()                       { return contactNumber; }
    public void setContactNumber(long contactNumber)     { this.contactNumber = contactNumber; }

    public int getQueueNumber()                          { return queueNumber; }
    public void setQueueNumber(int queueNumber)          { this.queueNumber = queueNumber; }

    @Override
    public String toString() {
        return String.format("%-8d %-16s %-14d %-6d", id, name, contactNumber, queueNumber);
    }
}
