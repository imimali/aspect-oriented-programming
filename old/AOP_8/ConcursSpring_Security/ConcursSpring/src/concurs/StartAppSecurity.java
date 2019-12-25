package concurs;

import javax.swing.SwingUtilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import concurs.ctrl.ConcursCtrl;
import concurs.ctrl.ViewResultsHandler;
import concurs.gui.ClasamentWindow;
import concurs.gui.ConcursWindow;

public class StartAppSecurity {

	    public static void main(String[] args) {
	    	
	    	final ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:SpringAspect*.xml");

	        /*ParticipantRepository parts=new ParticipantRepositoryMock();
	        Concurs concurs=new Concurs(parts);
	        final ConcursCtrl ctrl=new ConcursCtrl(concurs);*/
	        SwingUtilities.invokeLater(new Runnable(){
	            public void run() {
	                ConcursWindow cwin=new ConcursWindow((ConcursCtrl)factory.getBean("concursCtrl"));
	                cwin.setSize(300,400);
	                cwin.setLocation(150,150);
	                cwin.setVisible(true);
	            }
	        });

	       for(int i=0;i<1;i++){
	            //final ViewResultsHandler vrCtrl=new ViewResultsHandler(concurs);
	            SwingUtilities.invokeLater(new Runnable(){
	                public void run() {
	                    ClasamentWindow cwin=new ClasamentWindow((ViewResultsHandler)factory.getBean("resultsHandler"));
	                    cwin.setSize(300,300);
	                    cwin.setLocation(175,175);
	                    cwin.setVisible(true);
	                }
	            });
	        }

	    }
	}


