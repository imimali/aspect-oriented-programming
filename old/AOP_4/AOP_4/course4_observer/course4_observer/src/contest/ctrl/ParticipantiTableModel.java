package contest.ctrl;

import contest.model.Participant;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 1:16:04 AM
 */
public class ParticipantiTableModel extends AbstractTableModel {
    private List<Participant> parts;
    private String[] cols={"Nr.","Id", "Nume"};
    public ParticipantiTableModel(List<Participant> parts) {
        this.parts=parts;
    }

    @Override
    public String getColumnName(int column) {
       return cols[column];
    }

    public int getRowCount() {
        return parts.size();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
           case 0 : return rowIndex+1;
           case 1: return parts.get(rowIndex).getID();
           case 2: return parts.get(rowIndex).getNume();
        }
        return null;  
    }
    public void addParticipant(Participant p){
        parts.add(p);
        fireTableDataChanged();
    }
   
    public Participant get(int index) {
        return parts.get(index);
    }
}
