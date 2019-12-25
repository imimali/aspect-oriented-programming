package concurs.model;

import java.util.List;
import java.util.Observer;

import concurs.ConcursExceptie;

public interface ConcursService {
	public void adaugaRezultat(String ID, int puncte) throws ConcursExceptie;
	public void adaugaParticipant(String code, String nume) throws ConcursExceptie;
	public List<Participant> getParticipants();
	public List<Participant> getParticipantsByPoints();
	 public Participant getParticipant(String idP) throws ConcursExceptie;
	 public void addObserver(Observer o);
	 public void deleteObserver(Observer o);
}
