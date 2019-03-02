package persistence;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repository {
    private static final String URL = "jdbc:postgresql://localhost:5432/aop";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public List<Candidate> getAll() {
        String sqlStatement = "SELECT * FROM candidates";
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sqlStatement)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int votes = rs.getInt("votes");
                candidates.add(new Candidate(id, name, votes));
            }
            return candidates;

        } catch (Exception sqle) {
            sqle.printStackTrace();
            return candidates;
        }
    }

    public Optional<Candidate> store(Candidate elem) {
        if (elem == null)
            throw new IllegalArgumentException("element must not be null");


        String sql = "INSERT INTO candidates(name) VALUES(?)";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, elem.getName());
            ps.executeUpdate();
            return Optional.empty();
        } catch (SQLException sqle) {

            return Optional.of(elem);
        }
    }

    public Optional<Candidate> update(Candidate elem) {
        if (elem == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        String sql = "UPDATE candidates SET name = ?, votes = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, elem.getName());
            ps.setInt(2, elem.getVotes());
            ps.setLong(3, elem.getId());
            ps.executeUpdate();
            return Optional.empty();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return Optional.of(elem);
        }
    }

    public Optional<Candidate> delete(Long id) {
        String sql = "DELETE from candidates where id=?";
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return Optional.empty();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return Optional.empty();
        }
    }
}
