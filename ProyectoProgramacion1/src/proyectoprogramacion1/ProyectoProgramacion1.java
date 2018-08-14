package proyectoprogramacion1;

import java.awt.*;
//import java.awt.Color;
//import java.awt.Font;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
import java.io.*;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;

import javax.swing.*;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.text.DefaultEditorKit;

public class ProyectoProgramacion1 extends JFrame{
    private JTextArea area = new JTextArea(20,120);
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
<<<<<<< HEAD
    private String currentFile = "ManuFranco";
=======
    private String currentFile = "EDITOR DE TEXTO";
>>>>>>> c88255f8b391e3d6555e507605ce99c04d3a6070
    private boolean changed = false;
    
    
    public ProyectoProgramacion1(){
    
        area.setFont(new Font("Monospaced",Font.PLAIN,12));
        JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll,BorderLayout.CENTER);
        
        JMenuBar JMB = new JMenuBar();
        setJMenuBar(JMB);
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMB.add(file);
        JMB.add(edit);
        
        file.add(New);
        file.add(Open);
        file.add(Save);
        file.add(Quit);
        file.add(SaveAs);
        file.addSeparator();
        
        for(int i=0;i<4;i++)
            file.getItem(i).setIcon(null);
        
        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);
        
        edit.getItem(0).setText("Cut out");
        edit.getItem(1).setText("Copy");
        edit.getItem(2).setText("Paste");
        
        JToolBar tool = new JToolBar();
        add(tool,BorderLayout.NORTH);
        tool.add(New);
        tool.add(Open);
        tool.add(Save);
        tool.addSeparator();
        
        JButton cut = tool.add(Cut), cop = tool.add(Copy), pas = tool.add(Paste);
        cut.setText(null); cut.setIcon(new ImageIcon(""));
        cop.setText(null); cop.setIcon(new ImageIcon(""));
        pas.setText(null); pas.setIcon(new ImageIcon(""));
        
        Save.setEnabled(false);
        SaveAs.setEnabled(false);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        area.addKeyListener(k1);
        setTitle(currentFile);
        setVisible(true);
        
        
    //COLOR CHOOSER 
        JButton btnColor = new JButton("Color de Fuente");
        JMB.add(btnColor);
//        btnColor.setBounds(10, 30, 125, 20);
        btnColor.setToolTipText("Clickea para desplegar la paleta de colores");
        btnColor.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                //Abre la paleta de colores
                Color colores=JColorChooser.showDialog(JMB, "Eligir un color para la fuente", Color.BLACK);
                //Cambia el color del boton para saber que color estamos usando
                btnColor.setBackground(colores);
                area.setForeground(colores);
//                lblNewLabel.setOpaque(true);
            }
        });
        
    }

    
    private KeyListener k1 = new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                changed = true;
                Save.setEnabled(true);
                SaveAs.setEnabled(true);
            }
         
        
        
    };
    Action Open = new AbstractAction("Open", new ImageIcon("open.gif")){
        
        public void actionPerformed(ActionEvent e){
            saveOld();
            if(dialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                readInFile(dialog.getSelectedFile().getAbsolutePath());
            }
            SaveAs.setEnabled(true);
        }
    };
    
    Action Save = new AbstractAction("Save",new ImageIcon("")){
        public void actionPerformed(ActionEvent e){
            if(!currentFile.equals("Untitled"))
                saveFile(currentFile);
            else
                saveFileAs();
        }
        
    };
    Action SaveAs = new AbstractAction("Save as..."){
      public void actionPerformed(ActionEvent e){
          saveFileAs();
      }
    };
    Action Quit = new AbstractAction("Quit"){
      public void actionPerformed(ActionEvent e){
          saveOld();
          System.exit(0);
      }  
    };
    Action New = new AbstractAction("New", new ImageIcon("")){
      public void actionPerformed(ActionEvent e){
          saveOld();
          area.setText("");
          currentFile = "Untitled";
          setTitle(currentFile);
          changed = false;
          Save.setEnabled(false);
          SaveAs.setEnabled(false);
      }  
    };
    ActionMap m = area.getActionMap();
        Action Cut = m.get(DefaultEditorKit.cutAction);
        Action Copy = m.get(DefaultEditorKit.copyAction);
        Action Paste = m.get(DefaultEditorKit.pasteAction);
    
    private void saveFileAs()
    {
        if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
            saveFile(dialog.getSelectedFile().getAbsolutePath());
    }
    private void saveOld()
    {
        if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
            saveFile(dialog.getSelectedFile().getAbsolutePath());
    }
    private void readInFile(String fileName)
    {
        try{
            FileReader r = new FileReader(fileName);
            area.read(r,null);
            r.close();
            currentFile=fileName;
            setTitle(currentFile);
            changed = false;
        }
        catch(IOException e){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this,"Editor can't find the file called "+fileName);
            
        }
            
    }
    private void saveFile(String fileName){
        try{
            FileWriter w = new FileWriter(fileName);
            area.write(w);
            w.close();
            currentFile = fileName;
            setTitle(currentFile);
            changed = false;
            Save.setEnabled(false);
        }
        catch(IOException e){
            
        }
    }

        

    public static void main(String[] args) {
        new ProyectoProgramacion1();
    }
}
