package concurs.repository.mock;

import concurs.repository.ParticipantRepository;
import concurs.repository.RepositoryException;
import concurs.model.Participant;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Oct 29, 2009
 * Time: 7:42:16 PM
 */
public class ParticipantRepositoryMock implements ParticipantRepository{
    private Map<String, Participant> participants;

    public ParticipantRepositoryMock() {
        this.participants = new TreeMap<String, Participant>();
        populate();
    }

    private void populate() {
        participants.put("123",new Participant("123","Pop Vasile"));
        participants.put("245", new Participant("245", "Ionescu Maria"));
        participants.put("345", new Participant("345", "Vasilescu Ioan"));
    }

    public void save(Participant p) {
        Participant ePart=participants.get(p.getID());
        if (ePart!=null)
            throw new RepositoryException("Participant" +p+" already exists!");
        participants.put(p.getID(),p);
        System.out.println("Participant added ");
    }

    public void update(String codePart, Participant p) {
        Participant ePart=participants.get(codePart);
        if (ePart==null)
            throw new RepositoryException("Participant "+codePart+" does not exist!");
        participants.put(codePart, p);
    }

    public List<Participant> getAll() {
        return new ArrayList<Participant>(participants.values());
    }

    public Participant findById(String code) {
        Participant part=participants.get(code);
        if (part==null)
            throw new RepositoryException("Participant "+code+ "does not exist!");
        return part;
    }

    public List<Participant> getByPoints() {
        List<Participant> lp=new ArrayList<Participant>(participants.values());
        Collections.sort(lp, new Comparator<Participant>(){
            public int compare(Participant p1, Participant p2) {
                return -(p1.getPunctaj()-p2.getPunctaj());
            }
        });
        return lp;
    }
}
