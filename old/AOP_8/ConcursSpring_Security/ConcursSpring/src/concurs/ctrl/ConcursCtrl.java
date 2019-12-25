package concurs.ctrl;

import concurs.model.ConcursService;
import concurs.model.Participant;
import concurs.model.ConcursUtile;
import concurs.gui.ConcursWindow;
import concurs.ConcursExceptie;

import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 12:58:16 AM
 */
public class ConcursCtrl {
   private ConcursService concurs;

    private ParticipantiTableModel partsTM;
    public ConcursCtrl(ConcursService concurs) {
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

    public void adaugaRezultat(String idP, int punct) throws ConcursExceptie {
        //System.out.println("[Ctrl] se adauga rezultat");
        concurs.adaugaRezultat(idP,punct);
    }

    public void adaugaParticipant(String idP, String numeP) throws ConcursExceptie {
        concurs.adaugaParticipant(idP,numeP);
        Participant p=concurs.getParticipant(idP);
        partsTM.addParticipant(p);
    }

    public void salveazaClasament(String numefis) throws ConcursExceptie {
        List<Participant> parts=concurs.getParticipantsByPoints();
        ConcursUtile.salveazaClasament(numefis, parts);
    }
}
