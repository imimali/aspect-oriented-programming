package concurs.gui;

import concurs.ctrl.ConcursCtrl;
import concurs.ConcursExceptie;

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
public class AdaugaParticipant extends JDialog {
    private ConcursWindow parinte;
    private ConcursCtrl ctrl;

    public AdaugaParticipant( ConcursWindow parinte, ConcursCtrl ctrl) {
        super(parinte,"Adauga participant", true);
        this.parinte = parinte;
        this.ctrl = ctrl;
        getContentPane().add(creeazaAdaugare());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    private JTextField id, nume;
    private JButton addPart, clear;
    private JPanel creeazaAdaugare(){
        JPanel pan=new JPanel();
        pan.setLayout(new GridLayout(3,1));
            JPanel linia1=UtileGUI.putInPanel(new JLabel("Id:"));
            linia1.add(id=new JTextField(7));

            pan.add(linia1);

            JPanel linia2=UtileGUI.putInPanel(new JLabel("Nume:"));
            linia2.add(nume=new JTextField(7));
            pan.add(linia2);

            JPanel linia3=UtileGUI.putInPanel(addPart=new JButton("Adauga"));
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
                     JOptionPane.showMessageDialog(AdaugaParticipant.this,"Trebuie sa introduceti un ID", "Eroare adaugare participant", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 String numeP=nume.getText();
                 if (empty(numeP)){
                     JOptionPane.showMessageDialog(AdaugaParticipant.this,"Trebuie sa introduceti numele", "Eroare adaugare participant", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 try {
                     ctrl.adaugaParticipant(idP, numeP);
                 } catch (ConcursExceptie ex) {
                     JOptionPane.showMessageDialog(AdaugaParticipant.this,ex.getMessage(), "Eroare adaugare participant", JOptionPane.ERROR_MESSAGE);
                     return;
                 }catch (RuntimeException ex) {
                     JOptionPane.showMessageDialog(AdaugaParticipant.this,ex.getMessage(), "Eroare adaugare participant", JOptionPane.ERROR_MESSAGE);
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
