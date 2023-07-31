package client;
import database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private Database database;
    private Connection connection;
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement listAllSt;

    public ClientService() {
        database = Database.getInstance();
        connection = database.getConnection();

        try {
            createSt = connection.prepareStatement("INSERT INTO client (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            getByIdSt = connection.prepareStatement("SELECT name FROM client WHERE id = ?");
            setNameSt = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
            deleteByIdSt = connection.prepareStatement("DELETE FROM client WHERE id = ?");
            listAllSt = connection.prepareStatement("SELECT id, name FROM client");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public long create(String name) throws SQLException {
        validateName(name);
        createSt.setString(1, name);
        createSt.executeUpdate();

        ResultSet generatedKeys = createSt.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getLong(1);
        } else {
            throw new SQLException("Creating client failed, no ID obtained.");
        }
    }

    public String getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        try (ResultSet rs = getByIdSt.executeQuery()) {
            if (!rs.next()) {
                throw new SQLException("Client not found with ID: " + id);
            }
            return rs.getString("name");
        }
    }

    public void setName(long id, String name) throws SQLException {
        validateName(name);
        setNameSt.setString(1, name);
        setNameSt.setLong(2, id);

        int rowsUpdated = setNameSt.executeUpdate();
        if (rowsUpdated == 0) {
            throw new SQLException("Client not found with ID: " + id);
        }
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);
        int rowsDeleted = deleteByIdSt.executeUpdate();
        if (rowsDeleted == 0) {
            throw new SQLException("Client not found with ID: " + id);
        }
    }

    public List<Client> listAll() throws SQLException {
        try (ResultSet rs = listAllSt.executeQuery()) {
            List<Client> clients = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                clients.add(new Client(id, name));
            }
            return clients;
        }
    }

    private void validateName(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new SQLException("Client name cannot be empty.");
        }
        if (name.length() > 100) {
            throw new SQLException("Client name is too long (maximum 100 characters).");
        }
    }

    public void close() throws SQLException {
        createSt.close();
        getByIdSt.close();
        setNameSt.close();
        deleteByIdSt.close();
        listAllSt.close();
    }
}
