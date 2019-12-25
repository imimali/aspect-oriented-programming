package persistence;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Candidate> getAll();

    Optional<Candidate> store(Candidate elem);

    Optional<Candidate> update(Candidate elem);

    Optional<Candidate> delete(Long id);
}
