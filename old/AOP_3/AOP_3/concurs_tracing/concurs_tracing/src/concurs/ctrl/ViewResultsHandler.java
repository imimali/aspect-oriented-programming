package concurs.ctrl;

import concurs.model.Concurs;

import javax.swing.table.TableModel;
import java.util.Observer;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Dec 8, 2011
 * Time: 12:26:15 AM
 */
public class ViewResultsHandler implements Observer {
    private Concurs concurs;
    private ParticipantiResTableModel partTM;
    public ViewResultsHandler(Concurs concurs) {
        this.concurs = concurs;
        partTM=new ParticipantiResTableModel(concurs.getParticipantsByPoints());
        concurs.addObserver(this);
    }

    public TableModel getParticipantiModel() {
          return partTM;
    }

    public void update(Observable o, Object arg) {
         partTM.setParticipanti(concurs.getParticipantsByPoints());
    }

    public void close() {
        concurs.deleteObserver(this);

    }
}
