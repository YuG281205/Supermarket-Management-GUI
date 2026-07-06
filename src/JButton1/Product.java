package JButton1;

import jakarta.persistence.*;

/**
 * Entity mapped to the "product" table.
 *
 * Table schema:
 *   product(p_id INT PK, p_name VARCHAR, p_price INT, p_stock INT)
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "p_id")
    private int id;

    @Column(name = "p_name")
    private String name;

    @Column(name = "p_price")
    private int price;

    @Column(name = "p_stock")
    private int stock;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Product() {}

    public Product(int id, String name, int price, int stock) {
        this.id    = id;
        this.name  = name;
        this.price = price;
        this.stock = stock;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public int getId()               { return id; }
    public void setId(int id)        { this.id = id; }

    public String getName()          { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice()            { return price; }
    public void setPrice(int price)  { this.price = price; }

    public int getStock()            { return stock; }
    public void setStock(int stock)  { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("%-8d %-16s %-10d %-6d", id, name, price, stock);
    }
}
