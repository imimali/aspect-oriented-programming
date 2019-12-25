package concurs;

import concurs.repository.ParticipantRepository;
import concurs.repository.mock.ParticipantRepositoryMock;
import concurs.repository.file.ParticipantRepositoryFile;
import concurs.model.Concurs;
import concurs.ctrl.ConcursCtrl;
import concurs.ctrl.ViewResultsHandler;
import concurs.gui.ConcursWindow;
import concurs.gui.ClasamentWindow;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 1:05:16 AM
 */
public class StartApp {
    public static void main(String[] args) {
      ParticipantRepository parts=new ParticipantRepositoryMock();
    	//  ParticipantRepository parts=new ParticipantRepositoryFile("Participanti.txt");
        Concurs concurs=new Concurs(parts);
        final ConcursCtrl ctrl=new ConcursCtrl(concurs);
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                ConcursWindow cwin=new ConcursWindow(ctrl);
                cwin.setSize(300,400);
                cwin.setLocation(150,150);
                cwin.setVisible(true);
            }
        });

       for(int i=0;i<1;i++){
            final ViewResultsHandler vrCtrl=new ViewResultsHandler(concurs);
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    ClasamentWindow cwin=new ClasamentWindow(vrCtrl);
                    cwin.setSize(300,300);
                    cwin.setLocation(175,175);
                    cwin.setVisible(true);
                }
            });
        }

    }
}
