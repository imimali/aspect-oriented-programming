package contest.gui;

import contest.ctrl.ViewResultsHandler;
import contest.repository.ParticipantRepository;

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
public class RankingWindow extends JFrame {
    private ViewResultsHandler ctrl;
    private JTable tabel;
    //private ParticipantRepository rp;
    public RankingWindow(ViewResultsHandler ctrl) {
        super("Contest Ranking");
        this.ctrl = ctrl;
     //   rp.getAll();
        add(creeazaTabel());
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                RankingWindow.this.ctrl.close();
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
