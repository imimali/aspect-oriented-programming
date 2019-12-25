package persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RepositoryJdbcImpl implements Repository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Candidate> getAll() {
        String query = "SELECT * FROM candidates";
        return jdbcOperations.query(query, this::mapRowToCandidate);
    }

    @Override
    public Optional<Candidate> store(Candidate elem) {
        String sql = "INSERT INTO candidates(id, name,votes) VALUES(?, ?, ?)";
        try {
            jdbcOperations.update(sql, elem.getId(), elem.getName(), elem.getVotes());
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.of(elem);
    }

    @Override
    public Optional<Candidate> update(Candidate elem) {
        String sql = "update candidates name = ?, votes= ? where id = ?";
        try {
            jdbcOperations.update(sql, elem.getName(), elem.getVotes(), elem.getId());
            return Optional.of(elem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Candidate> delete(Long id) {
        String sql = "DELETE from candidates where id = ?";
        try {
            jdbcOperations.update(sql, id);
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //don't judge me, at this moment I just don't care
        return Optional.empty();
    }

    private Candidate mapRowToCandidate(ResultSet resultSet, int rowIndex) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Integer votes = resultSet.getInt("votes");
        return new Candidate(id, name, votes);
    }
}
