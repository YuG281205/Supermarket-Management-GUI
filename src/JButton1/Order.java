package JButton1;

import jakarta.persistence.*;

/**
 * Entity mapped to the "orders" table.
 *
 * Table schema:
 *   orders(o_id INT PK, o_type VARCHAR, p_id INT FK, c_id INT FK, o_quantity INT)
 *
 * The FK columns are kept as plain integers to stay consistent with the
 * original schema (no lazy-loading surprises in a Swing app).
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "o_id")
    private int id;

    @Column(name = "o_type")
    private String type;

    @Column(name = "p_id")
    private int productId;

    @Column(name = "c_id")
    private int customerId;

    @Column(name = "o_quantity")
    private int quantity;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Order() {}

    public Order(int id, String type, int productId, int customerId, int quantity) {
        this.id         = id;
        this.type       = type;
        this.productId  = productId;
        this.customerId = customerId;
        this.quantity   = quantity;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public int getId()                    { return id; }
    public void setId(int id)             { this.id = id; }

    public String getType()               { return type; }
    public void setType(String type)      { this.type = type; }

    public int getProductId()             { return productId; }
    public void setProductId(int pid)     { this.productId = pid; }

    public int getCustomerId()            { return customerId; }
    public void setCustomerId(int cid)    { this.customerId = cid; }

    public int getQuantity()              { return quantity; }
    public void setQuantity(int qty)      { this.quantity = qty; }

    @Override
    public String toString() {
        return String.format("%-8d %-12s %-8d %-8d %-6d", id, type, productId, customerId, quantity);
    }
}
