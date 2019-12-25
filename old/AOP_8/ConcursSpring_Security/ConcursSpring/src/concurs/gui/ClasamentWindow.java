package concurs.gui;

import concurs.ctrl.ViewResultsHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Dec 8, 2011
 * Time: 12:25:24 AM
 */
public class ClasamentWindow extends JFrame {
    private ViewResultsHandler ctrl;
    private JTable tabel;

    public ClasamentWindow(ViewResultsHandler ctrl) {
        super("Clasament concurs");
        this.ctrl = ctrl;
        add(creeazaTabel());
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                ClasamentWindow.this.ctrl.close();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


   private JPanel creeazaTabel(){
       JPanel pan=new JPanel();
       pan.setLayout(new GridLayout(1,1));
       tabel=new JTable(ctrl.getParticipantiModel());
       JScrollPane pane=new JScrollPane(tabel);
       pan.add(pane);
       return pan;
   }

}
