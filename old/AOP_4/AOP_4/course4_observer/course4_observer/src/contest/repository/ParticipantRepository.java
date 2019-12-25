package contest.repository;

import contest.model.Participant;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Oct 29, 2009
 * Time: 7:37:24 PM
 */
public interface ParticipantRepository {
    public void save(Participant p);
    void update(String codePart, Participant p);
    List<Participant> getAll();
    Participant findById(String code);
    List<Participant> getByPoints();
}
