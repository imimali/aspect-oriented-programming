package persistence;

import customsec.RequiresAuthentication;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Candidate> getAll();

    @RequiresAuthentication
    Optional<Candidate> store(Candidate elem);

    @RequiresAuthentication
    Optional<Candidate> update(Candidate elem);

    @RequiresAuthentication
    Optional<Candidate> delete(Long id);
}
