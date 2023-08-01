package database;

import client.Client;
import client.ClientService;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:F:/java/test", "sa", "")
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
        try {
            performDatabaseOperations();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void performDatabaseOperations() throws SQLException {
        ClientService clientService = new ClientService();
        try {
            long clientId = clientService.create("Test Company");
            System.out.println("Created client with ID: " + clientId);

            String clientName = clientService.getById(clientId);
            System.out.println("Client name for ID " + clientId + ": " + clientName);

            clientService.setName(clientId, "Test Corporation");

            List<Client> clients = clientService.listAll();
            System.out.println("All clients:");
            for (Client client : clients) {
                System.out.println("ID: " + client.getId() + ", Name: " + client.getName());
            }

            clientService.deleteById(clientId);

            clients = clientService.listAll();
            System.out.println("All clients after deletion:");
            for (Client client : clients) {
                System.out.println("ID: " + client.getId() + ", Name: " + client.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
