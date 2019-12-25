package contest.model;

import contest.ContestException;
import contest.model.Participant;
import contest.repository.ParticipantRepository;
import contest.repository.RepositoryException;

import java.util.List;



/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 25, 2008
 * Time: 3:56:05 PM
 */
public class Contest{
	
    private ParticipantRepository participantRepository;


    public Contest(ParticipantRepository repository) {
       participantRepository=repository;
    }

    public void adaugaRezultat(String ID, int puncte) throws ContestException{
        try{
            Participant p=participantRepository.findById(ID);
            p.adaugaRezultat(puncte);
            participantRepository.update(p.getID(), p);
            /*setChanged();
            notifyObservers();
            */
        }catch(RepositoryException ex){
            throw new ContestException(ex.getMessage());
        }

    }

    public void adaugaParticipant(String code, String nume) throws ContestException{
        Participant part=new Participant(code,nume);
        try{
            participantRepository.save(part);
            /*setChanged();
            notifyObservers();
            */
        }catch(RepositoryException ex){
            throw new ContestException(ex.getMessage());
        }
    }

    public List<Participant> getParticipants(){
        return participantRepository.getAll();
    }

    public List<Participant> getParticipantsByPoints(){
        return participantRepository.getByPoints();
    }

    public Participant getParticipant(String idP) throws ContestException {
        try{
            return participantRepository.findById(idP);

        }catch(RepositoryException ex){
            throw new ContestException(ex.getMessage());
        }
    }
}
