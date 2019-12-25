package contest.ctrl;

import contest.model.Contest;

import javax.swing.table.TableModel;


/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Dec 8, 2011
 * Time: 12:26:15 AM
 */
public class ViewResultsHandler /*implements Observer*/ {
    private Contest concurs;
    
    private ParticipantiResTableModel partTM;
    public ViewResultsHandler(Contest concurs) {
        this.concurs = concurs;
        partTM=new ParticipantiResTableModel(concurs.getParticipantsByPoints());
        /*concurs.addObserver(this);*/
    }

    public TableModel getParticipantiModel() {
          return partTM;
    }

    /*public void update(Observable o, Object arg) {
         partTM.setParticipanti(concurs.getParticipantsByPoints());
    }*/
    
    public void loadParticipants(){
    	 partTM.setParticipanti(concurs.getParticipantsByPoints());
    }

    public void close() {
       /* concurs.deleteObserver(this);*/

    }
}
