package JButton1;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton helper that builds and provides the Hibernate SessionFactory.
 * Call HibernateUtil.getSessionFactory() from any class instead of opening
 * raw JDBC connections.
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() {}

    private static SessionFactory buildSessionFactory() {
        try {
            // Loads hibernate.cfg.xml from the classpath root
            return new Configuration().configure("hibernate/hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    /** Call once at application shutdown to release resources. */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
