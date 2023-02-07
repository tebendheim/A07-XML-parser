package org.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import static java.awt.event.WindowEvent.WINDOW_CLOSING;

public class GUI extends JFrame implements ActionListener {
    private JButton openButton, skrivButton, sendButton;
    private File chosenFile =null;
    private Control control = new Control(this);
    private JLabel path;
    private File newFile = null;
    private JTextField dato;
    private JPanel panel, datoContainer, idPanel;
    private JTable tabell;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private boolean opened = false;


    public GUI(){
        super("File Chooser");
        setLayout(new FlowLayout());
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        datoContainer = new JPanel(new FlowLayout());

        dato = new JTextField("31.12.2022");
        datoContainer.add(new JLabel("Dato"));
        datoContainer.add(dato);
        panel.add(datoContainer);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.white);

        panel.add(panel2);
        panel.add(panel1);
        openButton = new JButton("Open");
        panel1.add(openButton);
        // runButton = new JButton("Kjør");
        //panel1.add(runButton);
        skrivButton = new JButton(("Skriv til fil"));
        panel1.add(skrivButton);
        path = new JLabel();
        panel2.add(path);

        openButton.addActionListener(this);
        //runButton.addActionListener(this);
        skrivButton.addActionListener(this);
        add(panel);

    // Legger til Panel med Tabell.
        idPanel = new JPanel();
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.Y_AXIS));
        model = new DefaultTableModel();
        String[] columnames = {"Personnummer", "AnsattNr"};
        model.addColumn("Personnummer", new Object[5]);
        model.addColumn("Ansatt nr", new Object[5]);
        model.setColumnIdentifiers(columnames);
        tabell = new JTable(model);
        tabell.setPreferredSize(new Dimension(400, 300));
        scroll = new JScrollPane(tabell);
        scroll.setPreferredSize(tabell.getPreferredSize());
        scroll.setViewportView(tabell);
        tabell.getTableHeader().setVisible(true);
        sendButton = new JButton("Legg til ansattnummer");
        sendButton.addActionListener(this);
        //JComboBox idPanel = new JComboBox(id.toArray());
        idPanel.add(scroll);
        idPanel.add(sendButton);
        panel.add(idPanel);


        // Viser hele GUI.
        setSize(800, 800);
        setVisible(true);

        // setter default close operasjon.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void showPrint(String print){
        JOptionPane.showMessageDialog(this, print);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==openButton){
           boolean open =  open();
           if (open){
               kjor();
           }
       // }else if (e.getSource()==runButton){
            //kjor();}

        }else if (e.getSource()==skrivButton){
            nyFil();
            if (opened && newFile != null){
                control.skrivTilFil(newFile);

            }else{
                JOptionPane.showMessageDialog(this,"Kunne ikke skrive til fil");
            }

        }else if (e.getSource() == sendButton){
            for (int i=0; i<tabell.getRowCount(); i++){
                model.fireTableDataChanged();
                String id = (String) tabell.getValueAt(i, 0);
                String nr = (String) tabell.getValueAt(i,1);
                control.settAnsattNr(id, nr);

            }
            JOptionPane.showMessageDialog(this, "Ansatt nummer er lagt til");
        }
    }
    public void kjor() {

        String datoText = dato.getText();
        if (chosenFile == null) {
            JOptionPane.showMessageDialog(this, "Du må velge en fil først.");
        } else {
            control.kjorFirma(chosenFile, datoText);

            Set<String> id = control.hentPersonnummer();
            model = new DefaultTableModel();

            String[] columnames = {"Personnummer", "AnsattNr"};

            model.addColumn("Personnummer", id.toArray());
            model.addColumn("Ansatt nr", new Object[id.size()]);
            model.setColumnIdentifiers(columnames);
            tabell.setModel(model);
            tabell.repaint();
            scroll.setViewportView(tabell);
            panel.repaint();
            panel.updateUI();
            opened = true;

        }
    }
    public void settEmptyTabell(){
        model = new DefaultTableModel();

        String[] columnames = {"Personnummer", "AnsattNr"};

        model.addColumn("Personnummer", new Object[5]);
        model.addColumn("Ansatt nr", new Object[5]);
        model.setColumnIdentifiers(columnames);
        tabell.setModel(model);
        tabell.repaint();
        scroll.setViewportView(tabell);
        panel.repaint();
        panel.updateUI();
    }

    public boolean open(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue==JFileChooser.APPROVE_OPTION) {
            chosenFile = fileChooser.getSelectedFile();
            int conf = JOptionPane.showConfirmDialog(this, "You hava chosen: " + chosenFile.getAbsolutePath());
            if (conf == JOptionPane.YES_OPTION) {
                String fileName = chosenFile.getName();
                String filePath = chosenFile.getAbsolutePath();
                path.setText("You hava chosen: " + fileName);
//                nyFil();
                if (chosenFile == null) {
                    opened = false;
                }
                return true;
            } else if (JOptionPane.NO_OPTION == conf || JOptionPane.CANCEL_OPTION == conf) {
                path.setText("No file is chosen");
                opened = false;
            } else {
                JOptionPane.showMessageDialog(this, "Error creating new file");

            }
        }
        settEmptyTabell();
        return false;

    }
    public File nyFil(){
        if (newFile == null){
            String fileName = chosenFile.getName();
            String filePath = chosenFile.getAbsolutePath();
            path.setText("You hava chosen: " + fileName);
            int dotIndex = filePath.lastIndexOf(".");
            String newFileName = null;
            if (dotIndex >= 0) {
                newFileName = filePath.substring(0, dotIndex) + "_for_import.csv";
            } else {
                newFileName = filePath + "_for_import.csv";
            }
            newFile = new File(newFileName);
            boolean check = true;
            while (check) {
                path.setText(newFileName);
                if (newFile.exists()) {
                    dotIndex = newFileName.lastIndexOf(".");
                    newFileName = newFileName.substring(0, dotIndex) + "1.csv";
                    newFile = new File(newFileName);
                } else {
                    check = false;
                }
            }
            try {
                boolean nyFil = newFile.createNewFile();
            } catch (IOException err) {
                JOptionPane.showMessageDialog(this, "Kunne ikke lage ny fil");
            }
        }
        return newFile;
    }
    public void setDialog(String dialog){
        JOptionPane.showMessageDialog(this, dialog);
    }


}
