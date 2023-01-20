package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener {
    private JButton openButton, runButton;
    private File chosenFile =null;
    private Control control = new Control(this);
    private JLabel path;
    public GUI(){
        super("File Chooser");
        setLayout(new FlowLayout());
        openButton = new JButton("Open");
        add(openButton);
        runButton = new JButton("Kjør");
        add(runButton);
        path = new JLabel();
        add(path);
        openButton.addActionListener(this);
        runButton.addActionListener(this);
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void showPrint(String print){
        JOptionPane.showMessageDialog(this, print);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==openButton){
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue==JFileChooser.APPROVE_OPTION){
                chosenFile= fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "You hava chosen: " + chosenFile.getAbsolutePath());
                path.setText("You hava chosen: " + chosenFile.getAbsolutePath());
                System.out.println(chosenFile.getAbsolutePath());
//                try{


            }else{
                JOptionPane.showMessageDialog(this, "Error creating new file");
            }
        }else if (e.getSource()==runButton){
            if (chosenFile==null){
                JOptionPane.showMessageDialog(this, "Du må velge en fil først.");

            }else{
                control.kjorFirma(chosenFile);
            }
        }
    }


}
