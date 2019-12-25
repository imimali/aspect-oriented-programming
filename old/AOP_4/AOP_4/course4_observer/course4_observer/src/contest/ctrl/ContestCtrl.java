package contest.ctrl;

import contest.ContestException;
import contest.gui.ContestWindow;
import contest.model.Contest;
import contest.model.ContestUtils;
import contest.model.Participant;

import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 12:58:16 AM
 */
public class ContestCtrl {
   private Contest concurs;

    private ParticipantiTableModel partsTM;
    public ContestCtrl(Contest concurs) {
        this.concurs=concurs;
        partsTM=new ParticipantiTableModel(concurs.getParticipants());
    }


    public TableModel getParticipantiTableModel() {
        return partsTM;
    }

    public String participantSelectat(int index) {
        Participant part=partsTM.get(index);
        return part.getID();

    }

    public void adaugaRezultat(String idP, int punct) throws ContestException {
        //System.out.println("[Ctrl] se adauga rezultat");
        concurs.adaugaRezultat(idP,punct);
    }

    public void adaugaParticipant(String idP, String numeP) throws ContestException {
        concurs.adaugaParticipant(idP,numeP);
        Participant p=concurs.getParticipant(idP);
        partsTM.addParticipant(p);
    }

    public void salveazaClasament(String numefis) throws ContestException {
        List<Participant> parts=concurs.getParticipantsByPoints();
        ContestUtils.salveazaClasament(numefis, parts);
    }
}
