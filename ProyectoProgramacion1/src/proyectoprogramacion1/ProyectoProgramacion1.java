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

    public static void main(String[] args) {
        //FRAME
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

//LABEL1
        JLabel label1 = new JLabel("Zanoni-Moroncini ©");
        frame.add(label1);
        label1.setBounds(340, 350, 200, 200);
        label1.setForeground(new Color(25, 114, 236));
        label1.setToolTipText("Trabajo hecho por Zanoni y Moroncini");

//TEXT AREA
        JTextArea txtarea = new JTextArea();
        frame.add(txtarea);    
        //txtarea.setBounds(5, 3, 260, 50);
        txtarea.setToolTipText("Aquí se muestra el texto que usted ingresa");
        txtarea.setEditable(true);
        

//SCROLL PANE
        JScrollPane scroll = new JScrollPane(txtarea);
        frame.add(scroll);
        scroll.setBounds(20, 60, 460, 230);
        scroll.setToolTipText("Sube y baja");

//DETALLES
//hacer que las palabras no se corten
        txtarea.setLineWrap(true);
        txtarea.setWrapStyleWord(true);
//fuente        
        Font fuente = new Font("Consolas", Font.BOLD, 30);
        txtarea.setFont(fuente);
        txtarea.setBackground(Color.yellow);
                  
//JComboBox
    JComboBox combbox = new JComboBox();
    frame.add(combbox);
//    combbox.setBounds(X,Y,Widht,High);
    combbox.setBounds(20,20,100,20);
    combbox.addItem("uno");
    combbox.addItem("dos");
       
   combbox.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent ae) {
       
   }
});  
               
//Hacer la ventana/frame visible.
        frame.setSize(500, 500);
        frame.repaint();
        frame.setVisible(true);
    }
}
    

