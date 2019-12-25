package contest.gui;

import contest.ContestException;
import contest.ctrl.ContestCtrl;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 12:58:31 AM
 */
public class ContestWindow extends JFrame {
    private ContestCtrl ctrl;
    public ContestWindow(ContestCtrl ctrl){
        super("Contest XYZ");
        this.ctrl=ctrl;
        setJMenuBar(creeazaMeniu());
        add(creeazaTabelParticipanti(), BorderLayout.CENTER);
        add(creeazaAdaugaRezultat(), BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private JTextField id, puncte;
    private JButton addRez;
    private JPanel creeazaAdaugaRezultat() {
            JPanel pan=new JPanel();
            pan.setLayout(new GridLayout(3,1));
            JPanel linia1= GUIUtils.putInPanel(new JLabel("Id:"));
            linia1.add(id=new JTextField(7));
            id.setEditable(false);
            pan.add(linia1);

            JPanel linia2= GUIUtils.putInPanel(new JLabel("Points:"));
            linia2.add(puncte=new JTextField(7));
            pan.add(linia2);

            JPanel linia3= GUIUtils.putInPanel(addRez = new JButton("Add"));
            addRez.addActionListener(new ButListener());
            pan.add(linia3);
            pan.setBorder(BorderFactory.createTitledBorder("Add results"));
            return pan;
    }
    private JTable tabelP;
    private JPanel creeazaTabelParticipanti() {
        JPanel pan=new JPanel();
        pan.setLayout(new GridLayout(1,1));
        tabelP=new JTable(ctrl.getParticipantiTableModel());
        tabelP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelP.getSelectionModel().addListSelectionListener(new ParticipantiLSM());
        JScrollPane pane=new JScrollPane(tabelP);
        pan.add(pane);
        return pan;
    }

    private JMenuBar creeazaMeniu() {
        JMenuBar mb=new JMenuBar();
        JMenu part=new JMenu("Participants");
        JMenuItem adaugPart=new JMenuItem("Add participant");
        part.add(adaugPart);
        JMenu clasamente=new JMenu("Standings");
        JMenuItem clas1=new JMenuItem("Sorted by points");
        clasamente.add(clas1);
        mb.add(part);
        mb.add(clasamente);
        adaugPart.setActionCommand("adauga");
        clas1.setActionCommand("ordonatiP");
        ActionListener al=new MeniuListener();
        adaugPart.addActionListener(al);
        clas1.addActionListener(al);
        return mb;
    }

    private class MeniuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd=e.getActionCommand();
            if ("adauga".equals(cmd)){
                AddParticipantWindow addWin=new AddParticipantWindow(ContestWindow.this, ctrl);
                addWin.setSize(200,200);
                addWin.setLocation(175,175);
                addWin.setVisible(true);
                return;
            }
            if ("ordonatiP".equals(cmd)){
                JFileChooser jf=new JFileChooser(".");
                int rez=jf.showSaveDialog(ContestWindow.this);
                if (rez==JFileChooser.APPROVE_OPTION){
                    File f=jf.getSelectedFile();
                    String numefis=f.getAbsolutePath();
                    try {
                        ctrl.salveazaClasament(numefis);
                    } catch (ContestException ex) {
                        JOptionPane.showMessageDialog(ContestWindow.this,ex.getMessage(), "Eroare salvare clasament", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }
    }

    private class ParticipantiLSM implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()){
                int index=tabelP.getSelectedRow();
                if (index>=0){
                    String idSel=ctrl.participantSelectat(index);
                    id.setText(idSel);
                }

            }
        }
    }

    private class ButListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String idP=id.getText();
            String spuncte=puncte.getText();
            if (tabelP.getSelectedRow()<0){
                JOptionPane.showMessageDialog(ContestWindow.this,"You must select a participant", "Error adding result", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try{
                int punct=Integer.parseInt(spuncte);
                ctrl.adaugaRezultat(idP,punct);
                puncte.setText("");
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(ContestWindow.this,"The number of points must be an integer number ", "Error adding result", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (ContestException ex) {
                JOptionPane.showMessageDialog(ContestWindow.this,ex.getMessage(), "Error adding result", JOptionPane.ERROR_MESSAGE);

            }

        }
    }
}
