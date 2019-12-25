package contest.gui;

import contest.ContestException;
import contest.ctrl.ContestCtrl;
import contest.repository.ParticipantRepository;
import contest.repository.mock.ParticipantRepositoryMock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 1:48:17 AM
 */
public class AddParticipantWindow extends JDialog {
    private ContestWindow parinte;
    private ParticipantRepository rp=new ParticipantRepositoryMock();
    private ContestCtrl ctrl;

    public AddParticipantWindow(ContestWindow parinte, ContestCtrl ctrl) {
        super(parinte,"Add participant", true);
        this.parinte = parinte;
        //rp.getAll();
        this.ctrl = ctrl;
        getContentPane().add(creeazaAdaugare());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    private JTextField id, nume;
    private JButton addPart, clear;
    private JPanel creeazaAdaugare(){
        JPanel pan=new JPanel();
        pan.setLayout(new GridLayout(3,1));
            JPanel linia1= GUIUtils.putInPanel(new JLabel("Id:"));
            linia1.add(id=new JTextField(7));

            pan.add(linia1);

            JPanel linia2= GUIUtils.putInPanel(new JLabel("Name:"));
            linia2.add(nume=new JTextField(7));
            pan.add(linia2);

            JPanel linia3= GUIUtils.putInPanel(addPart = new JButton("Add"));
            linia3.add(clear=new JButton("Clear"));
            ActionListener al=new ButListener();
            addPart.addActionListener(al);
            clear.addActionListener(al);
            pan.add(linia3);
        return pan;
    }

    private class ButListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
             if (e.getSource()==addPart){
                 String idP=id.getText();
                 if (empty(idP)){
                     JOptionPane.showMessageDialog(AddParticipantWindow.this,"You must introduce an ID", "Error adding participant", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 String numeP=nume.getText();
                 if (empty(numeP)){
                     JOptionPane.showMessageDialog(AddParticipantWindow.this,"You must fill in the name", "Error adding participant", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 try {
                     ctrl.adaugaParticipant(idP, numeP);
                 } catch (ContestException ex) {
                     JOptionPane.showMessageDialog(AddParticipantWindow.this,ex.getMessage(), "Error adding participant", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 return;
             }
            if (e.getSource()==clear){
                id.setText("");
                nume.setText("");
            }
        }
    }

    private boolean empty(String s){
        return (s==null)|| (s.trim().length()==0);
    }
}
