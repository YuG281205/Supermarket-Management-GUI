# GHUMA Supermarket — GUI Billing Application

## What this is
A lightweight Java Swing desktop GUI for supermarket management: customers, products, orders and billing with Hibernate + MySQL persistence. Intended for small demos, learning Java Swing + Hibernate, or as a starting point for a local, single-machine supermarket billing tool.

### Stack
- **Language(s):** Java (primary)
- **Framework / runtime:** Java 17, Swing (desktop GUI)
- **Notable libraries:** Hibernate ORM 6.x, MySQL Connector/J, Jakarta Persistence API, SLF4J simple logger

## Features
- CRUD for Customers, Products and Orders via a Swing UI
- Queue billing and total billing screens
- Simple invoice printing and calculator with history
- Persistence via Hibernate to a MySQL database

## How it's organized
```
src/                      # Java source (not standard Maven layout)
  JButton1/               # GUI classes + model entities + app entry point
    JButton2.java         # Main application window and navigation (main class)
    JButtonCustomer.java  # Customer insertion / listing window
    InsertProduct.java    # Product insertion UI
    InsertOrders.java     # Order insertion UI
    Update_*.java         # Update screens for Customer/Product/Order
    Delete_*.java         # Delete screens
    QueueBilling.java     # Billing queue UI
    BillingPanel.java     # Total billing UI
    CalculatorWithHistory.java
  hibernate/              # Hibernate configuration
    hibernate.cfg.xml     # DB connection + mapped classes
pom.xml                   # Maven build file (Java 17, mainClass set to JButton1.JButton2)
target/                   # Maven build outputs
*.{png,jpg,webp}          # UI screenshots and logos in repo root
```

How it fits together: The Swing application (JButton1.JButton2) is the entry point and opens windows that call other classes under src/JButton1. Domain model classes (Customer, Product, Order) are simple POJOs mapped in `src/hibernate/hibernate.cfg.xml`; Hibernate manages persistence to a local MySQL database. The pom sets `src` as the sourceDirectory so Maven compiles the top-level `src` folder.

## Requirements
- Java 17 (JDK)
- Maven 3.6+
- MySQL server (or compatible) with a database named `supermarket` (or update URL in hibernate.cfg.xml)

## Quick start — run locally
1. Clone the repository

```sh
git clone https://github.com/YuG281205/Supermarket-Management-GUI.git
cd Supermarket-Management-GUI
```

2. Configure the database
- Edit `src/hibernate/hibernate.cfg.xml` and set `hibernate.connection.url`, `username`, and `password` for your MySQL instance. The repository currently contains example credentials — do not use those in production.

3. Build

```sh
mvn clean package
```

4. Run

```sh
# Run the packaged jar (artifactId=ghuma-supermarket; version from pom.xml)
java -jar target/ghuma-supermarket-1.0-SNAPSHOT.jar
```

If you prefer to run from classes (useful during development):
```sh
# run from classes if you haven't created a runnable jar with dependencies
mvn -q -DskipTests=true package
java -cp target/classes:$(mvn -q dependency:build-classpath -Dmdep.outputFile=cp.txt && tr ':' '\n' < cp.txt | tr '\n' ':') JButton1.JButton2
```
(Alternatively open the project in your IDE and set `JButton1.JButton2` as the run configuration.)

## Configuration notes
- The Hibernate config is at `src/hibernate/hibernate.cfg.xml`. It contains the DB URL, username, and password and declares mapped classes: `JButton1.Customer`, `JButton1.Product`, `JButton1.Order`.
- For production or sharing, remove credentials from the repo and load them from environment variables or an external configuration file.
- The pom uses `<sourceDirectory>src</sourceDirectory>` (nonstandard). Import into IDE accordingly or update to the standard `src/main/java` layout for compatibility.

## Building a standalone (fat) jar
This project currently produces a plain jar containing your classes (not dependencies). To make an executable fat jar include an assembly or shade plugin in `pom.xml` (e.g., maven-shade-plugin) and rebuild.

## Troubleshooting
- "ClassNotFound" errors: ensure dependencies are on the classpath or build a fat jar.
- DB connection problems: verify MySQL is running, the `supermarket` database exists, and credentials/URL are correct.
- If the UI images are missing at runtime, copy image files (logo/supermarket1.png etc.) into the working directory or update resource loading.

## Contributing
Feel free to open issues or PRs. Suggested improvements:
- Externalize configuration (use env vars or application.properties)
- Migrate to standard Maven layout and add tests
- Use a connection pool (HikariCP) and proper logging
- Provide SQL schema or Liquibase/Flyway migrations

## Security / License
- WARNING: `src/hibernate/hibernate.cfg.xml` currently contains hard-coded DB credentials. Remove or rotate them before sharing or deploying.
- No license file included. Add a LICENSE to clarify reuse.

---

If you want, I can:
- add a LICENSE file (MIT/Apache2),
- add a simple SQL schema file or a script to create the `supermarket` database and tables, or
- update the project to use environment variables for DB configuration and show how to run it in an IDE.
