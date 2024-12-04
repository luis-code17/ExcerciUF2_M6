package dao;
import model.Athlete;
import model.Sport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AthleteDAO implements DAO<Athlete> {
    private Connection conn;

    public AthleteDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Athlete item) {
        String query = "INSERT INTO deportistas (nombre, cod_deporte) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getCodeSport());
            ps.executeUpdate();
            System.out.println("Deportista añadido correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Athlete> getAll() {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT d.cod, d.nombre, d.cod_deporte, ds.nombre AS Snombre " +
                "FROM deportistas AS d " +
                "JOIN deportes AS ds ON d.cod_deporte = ds.cod;";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("nombre");
                int code = resultSet.getInt("cod");
                int codeSport = resultSet.getInt("cod_deporte");
                String sportName = resultSet.getString("Snombre");
                Athlete athlete = new Athlete(name, code, codeSport, sportName);
                athletes.add(athlete);
            }
            return athletes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Boolean exists(Athlete item) {
        String query = "SELECT * FROM deportistas WHERE nombre = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Athlete> getAthleteBySport(int codeSport) {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT * FROM listar_deportistas_por_deporte(?);";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, codeSport);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("nombre_deportista");
                int code = resultSet.getInt("cod");
                String sportName = resultSet.getString("nombre_deporte");

                Athlete athlete = new Athlete(name, code, codeSport, sportName);
                athletes.add(athlete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return athletes;
    }

    public int LastCode() {
        String query = "SELECT MAX(cod) FROM deportistas";
        int code = 0;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                code = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
        }

    public String getSportName(int codeSport) {
        String query = "SELECT nombre FROM deportes WHERE cod = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, codeSport);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {  // Verifica si hay un resultado
                return rs.getString(1);  // Retorna el nombre del deporte
            } else {
                // Si no hay resultados, retorna null o un mensaje adecuado
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Athlete> getAthletesByPartialName(String partialName) {
        String query = "SELECT d.cod, d.nombre, d.cod_deporte, ds.nombre AS sport_name " +
                "FROM deportistas d " +
                "JOIN deportes ds ON d.cod_deporte = ds.cod " +
                "WHERE d.nombre ILIKE ?;";

        ArrayList<Athlete> athletes = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Usamos % para permitir búsqueda parcial
            stmt.setString(1, "%" + partialName + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int code = rs.getInt("cod");
                String athleteName = rs.getString("nombre");
                int codeSport = rs.getInt("cod_deporte");
                String sportName = rs.getString("sport_name");

                athletes.add(new Athlete(athleteName, code, codeSport, sportName));
            }

            if (athletes.isEmpty()) {
                System.out.println("No se encontraron deportistas.");
            }
            return athletes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}