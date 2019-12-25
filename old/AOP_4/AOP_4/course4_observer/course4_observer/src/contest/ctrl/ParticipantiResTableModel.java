package contest.ctrl;

import contest.model.Participant;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Dec 8, 2011
 * Time: 12:31:34 AM
 */
public class ParticipantiResTableModel extends AbstractTableModel {

    private List<Participant> parts;
    private String[] cols={"Nr.","Id", "Nume","Puncte", "Nr.Teste"};

    public ParticipantiResTableModel(List<Participant> parts) {
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
           case 3: return parts.get(rowIndex).getPunctaj();
           case 4: return parts.get(rowIndex).getNrProbe();
        }
        return null;
    }
    public void setParticipanti(List<Participant> parts){
        this.parts=parts;
        fireTableDataChanged();
    }

   
}
