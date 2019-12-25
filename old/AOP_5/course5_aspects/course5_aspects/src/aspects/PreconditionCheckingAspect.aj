package aspects;
import contest.model.*;
import contest.repository.*;
import contest.ContestException;;


privileged public  aspect PreconditionCheckingAspect {
	pointcut adaugaRezultat(Contest conc, String ID, int puncte): execution(* Contest.adaugaRezultat(..))&& target(conc)&& args(ID, puncte);

	void around(Contest conc, String ID, int puncte) throws ContestException:adaugaRezultat(conc,ID,puncte){
		ParticipantRepository pRepo=conc.participantRepository;
		
		try{
			pRepo.findById(ID);
		}catch(RepositoryException ex){	
				throw new ContestException(ex.getMessage());
		}
		proceed(conc,ID,puncte);
	
	}
}
