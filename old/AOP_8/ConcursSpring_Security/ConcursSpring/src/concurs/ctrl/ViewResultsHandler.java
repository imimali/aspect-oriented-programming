package concurs.ctrl;

import concurs.model.ConcursService;
import concurs.model.Participant;

import javax.swing.table.TableModel;
import java.util.Observer;
import java.util.List;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Dec 8, 2011
 * Time: 12:26:15 AM
 */
public class ViewResultsHandler implements Observer {
    private ConcursService concurs;
    private ParticipantiResTableModel partTM;
    public ViewResultsHandler(ConcursService concurs) {
        this.concurs = concurs;
        partTM=new ParticipantiResTableModel(concurs.getParticipantsByPoints());
        concurs.addObserver(this);
        System.out.println("Observer added");
    }

    public TableModel getParticipantiModel() {
          return partTM;
    }

    public void update(Observable o, Object arg) {
    	List<Participant> lista=concurs.getParticipantsByPoints();
    	//System.out.println("update called ..."+lista);
    	
         partTM.setParticipanti(lista);
    }

    public void close() {
        concurs.deleteObserver(this);

    }
}
