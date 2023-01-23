package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.awt.event.WindowEvent.WINDOW_CLOSING;

public class GUI extends JFrame implements ActionListener {
    private JButton openButton, runButton;
    private File chosenFile =null;
    private Control control = new Control(this);
    private JLabel path;
    private File newFile = null;

    public GUI(){
        super("File Chooser");
        setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.white);

        panel.add(panel1);
        panel.add(panel2);
        openButton = new JButton("Open");
        panel1.add(openButton);
        runButton = new JButton("Kjør");
        panel1.add(runButton);
        path = new JLabel();
        panel2.add(path);

        openButton.addActionListener(this);
        runButton.addActionListener(this);
        add(panel);

        setSize(600, 400);
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
                String fileName = chosenFile.getName();
                String filePath = chosenFile.getAbsolutePath();
                path.setText("You hava chosen: " + fileName);
                int dotIndex = filePath.lastIndexOf(".");
                String newFileName = null;
                if (dotIndex >=0){
                    newFileName = filePath.substring(0, dotIndex)+"_for_import.csv";
                }else {
                    newFileName = filePath+"_for_import.csv";
                }

                newFile = new File(newFileName);
                boolean check = true;
                while (check){
                    path.setText(newFileName);
                    if (newFile.exists()){
                        dotIndex = newFileName.lastIndexOf(".");
                        newFileName = newFileName.substring(0,dotIndex)+"1.csv";
                        newFile = new File(newFileName);
                    }else{
                        check = false;
                    }
                }


//                try{


            }else{
                JOptionPane.showMessageDialog(this, "Error creating new file");
            }
        }else if (e.getSource()==runButton){
            if (chosenFile==null || newFile == null){
                JOptionPane.showMessageDialog(this, "Du må velge en fil først.");

            }else{
                try{
                    boolean nyFil= newFile.createNewFile();

                }catch (IOException err){
                    JOptionPane.showMessageDialog(this,"Kunne ikke lage ny fil");
                }


                control.kjorFirma(chosenFile, newFile);
            }
        }
    }
    public void setDialog(String dialog){
        JOptionPane.showMessageDialog(this, dialog);
    }


}
