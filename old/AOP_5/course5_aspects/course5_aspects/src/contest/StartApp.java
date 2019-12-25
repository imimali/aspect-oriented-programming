package contest;

import contest.ctrl.ContestCtrl;
import contest.ctrl.ViewResultsHandler;
import contest.gui.RankingWindow;
import contest.gui.ContestWindow;
import contest.model.Contest;
import contest.repository.ParticipantRepository;
import contest.repository.mock.ParticipantRepositoryMock;

import javax.swing.*;

public class StartApp {
    public static void main(String[] args) {
        ParticipantRepository parts=new ParticipantRepositoryMock();
        Contest concurs=new Contest(parts);
        final ContestCtrl ctrl=new ContestCtrl(concurs);
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                ContestWindow cwin=new ContestWindow(ctrl);
                cwin.setSize(300,400);
                cwin.setLocation(150,150);
                cwin.setVisible(true);
            }
        });

       for(int i=0;i<1;i++){
            final ViewResultsHandler vrCtrl=new ViewResultsHandler(concurs);
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    RankingWindow cwin=new RankingWindow(vrCtrl);
                    cwin.setSize(300,300);
                    cwin.setLocation(175,175);
                    cwin.setVisible(true);
                }
            });
        }

    }
}
