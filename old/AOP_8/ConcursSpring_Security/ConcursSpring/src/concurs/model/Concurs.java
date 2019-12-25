package concurs.model;

import concurs.model.Participant;
import concurs.ConcursExceptie;
import concurs.repository.ParticipantRepository;
import concurs.repository.RepositoryException;

import java.util.List;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 25, 2008
 * Time: 3:56:05 PM
 */
public class Concurs extends Observable implements ConcursService{
    private ParticipantRepository participantRepository;


    public Concurs(ParticipantRepository repository) {
       participantRepository=repository;
    }

    public void adaugaRezultat(String ID, int puncte) throws ConcursExceptie{
        try{
            Participant p=participantRepository.findById(ID);
            p.adaugaRezultat(puncte);
            participantRepository.update(p.getID(), p);
            setChanged();
            notifyObservers();
        }catch(RepositoryException ex){
            throw new ConcursExceptie(ex.getMessage());
        }

    }

    public void adaugaParticipant(String code, String nume) throws ConcursExceptie{
        Participant part=new Participant(code,nume);
        try{
            participantRepository.save(part);
            setChanged();
            notifyObservers();
        }catch(RepositoryException ex){
            throw new ConcursExceptie(ex.getMessage());
        }
    }

    public List<Participant> getParticipants(){
        return participantRepository.getAll();
    }

    public List<Participant> getParticipantsByPoints(){
        return participantRepository.getByPoints();
    }

    public Participant getParticipant(String idP) throws ConcursExceptie {
        try{
            return participantRepository.findById(idP);

        }catch(RepositoryException ex){
            throw new ConcursExceptie(ex.getMessage());
        }
    }
}
