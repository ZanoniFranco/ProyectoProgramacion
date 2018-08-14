package proyectoprogramacion1;

import java.awt.*;
//import java.awt.Color;
//import java.awt.Font;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
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

public class ProyectoProgramacion1 {
    public ProyectoProgramacion1(){
    
    //FRAME
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

//TEXT AREA
        JTextArea txtarea = new JTextArea();
        frame.add(txtarea);
        //(los bounds del txtxarea dependen de los bounds del scrollpane)
        //txtarea.setBounds(5, 3, 260, 50); 
        txtarea.setToolTipText("Hoja");
        txtarea.setEditable(true);
        txtarea.setBackground(Color.WHITE);

//SCROLL PANE
        JScrollPane scroll = new JScrollPane(txtarea);
        frame.add(scroll);
        scroll.setBounds(20, 60, 455, 380);
        scroll.setToolTipText("Sube y baja");

//DETALLES
//hacer que las palabras no se corten
        txtarea.setLineWrap(true);
        txtarea.setWrapStyleWord(true);
//fuente        
        Font fuente = new Font("Consolas", Font.BOLD, 30);
        txtarea.setFont(fuente);

//LABEL1
        JLabel label1 = new JLabel("Zanoni-Moroncini ©");
        frame.add(label1);
        label1.setBounds(340, 350, 200, 200);
        label1.setForeground(new Color(25, 114, 236));
        label1.setToolTipText("Trabajo hecho por Zanoni y Moroncini");

//LABEL2
        JLabel label2 = new JLabel ("FUENTE");
        frame.add(label2);
        label2.setBounds(20, 3, 150, 25);
        label2.setForeground(new Color(1));
        label2.setToolTipText("Despliega el menu debajo para elegir un tipo de fuente");
        
//JComboBox
        JComboBox combbox = new JComboBox();
        frame.add(combbox);
//    combbox.setBounds(X,Y,Widht,High);
        combbox.setBounds(20, 25, 80, 20);
        combbox.addItem("uno");
        combbox.addItem("dos");
        combbox.setToolTipText("Elige una fuente");

        combbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });

//COLOR CHOOSER 
        JButton btnColor = new JButton("Color de Fuente");
        frame.add(btnColor);
        btnColor.setBounds(110, 25, 125, 20);
        btnColor.setToolTipText("Clickea para desplegar la paleta de colores");
        
        JColorChooser colores = new JColorChooser();
//        frame.add(colores);
//    combbox.setBounds(X,Y,Widht,High);
    combbox.setBounds(20,20,100,20);
    combbox.addItem("uno");
    combbox.addItem("dos");
       
   combbox.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent ae) {
       
   }
});  
               
        colores.setBounds(60, 200, 100, 20);
        colores.setToolTipText("Elige un color de fuente");
//Hacer la ventana/frame visible.
        frame.setSize(500, 500);
        frame.repaint();
        frame.setVisible(true);
    
    }

    public static void main(String[] args) {
        new ProyectoProgramacion1();
    }
}
