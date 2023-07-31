package database;

import org.flywaydb.core.Flyway;

public class RunFlyaway {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:F:/java/test", "sa", "")
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();

    }
}

