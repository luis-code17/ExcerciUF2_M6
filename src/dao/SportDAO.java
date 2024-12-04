package dao;
import model.Sport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDAO implements DAO<Sport> {

    private Connection conn;

    public SportDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Sport item) {
        String query = "INSERT INTO deportes (nombre) VALUES ('" + item.getName() + "');";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Deporte insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Sport> getAll() {
        List<Sport> sports = new ArrayList<>();
        String query = "SELECT * FROM listar_deportes();";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("nombre");
                int code = resultSet.getInt("cod");
                Sport sport = new Sport(name, code);
                sports.add(sport);
            }
            return sports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean exists(Sport item) {
        String query = "SELECT * FROM deportes WHERE nombre = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int LastCode() {
        String query = "SELECT MAX(cod) FROM deportes;";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


}