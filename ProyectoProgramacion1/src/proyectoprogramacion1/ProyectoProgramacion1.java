package proyectoprogramacion1;

import java.awt.*;
import static java.awt.Color.*;
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

public class ProyectoProgramacion1 extends JFrame {

    private JTextArea area = new JTextArea(20, 80);
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));

    private String currentFile = "EDITOR DE TEXTO";

    private boolean changed = false;

    public ProyectoProgramacion1() {

        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setToolTipText("Hoja");
        JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setToolTipText("Sube y baja");
        add(scroll, BorderLayout.CENTER);

        JMenuBar JMB = new JMenuBar();
        setJMenuBar(JMB);
        JMenu file = new JMenu("Archivo");
        JMenu edit = new JMenu("Editar");
        JMB.setToolTipText("Barra de tareas");
        JMB.add(file);
        JMB.add(edit);
        file.setToolTipText("Archivo");
        edit.setToolTipText("Editar");

        file.add(New);
        file.add(Open);
        file.add(Save);
        file.add(Quit);
        file.add(SaveAs);
        file.addSeparator();

        for (int i = 0; i < 4; i++) {
            file.getItem(i).setIcon(null);
        }

        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);

        edit.getItem(0).setText("Cortar");
        edit.getItem(0).setToolTipText("Ctrl + X");
        edit.getItem(1).setText("Copiar");
        edit.getItem(1).setToolTipText("Ctrl + C");
        edit.getItem(2).setText("Pegar");
        edit.getItem(2).setToolTipText("Ctrl + V");

        JToolBar tool = new JToolBar();
        add(tool, BorderLayout.NORTH);
//        tool.add(New);
        tool.add(New).setToolTipText("Nuevo");
//        tool.add(Open);
        tool.add(Open).setToolTipText("Abrir");
//        tool.add(Save);
        tool.add(Save).setToolTipText("Guardar");
        tool.addSeparator();

        JButton cut = tool.add(Cut), cop = tool.add(Copy), pas = tool.add(Paste);
        cut.setToolTipText("Corta el texto seleccionado");
        cop.setToolTipText("Copia el texto seleccionado");
        pas.setToolTipText("Pega el texto que hay en el cortapapeles");
        cut.setText(null);
        cut.setIcon(new ImageIcon("C:\\Users\\Manuel\\Documents\\NetBeansProjects\\ProyectoProgramacion\\ProyectoProgramacion1\\src\\proyectoprogramacion1\\cortar.png"));
        cop.setText(null);
        cop.setIcon(new ImageIcon("C:\\Users\\Manuel\\Documents\\NetBeansProjects\\ProyectoProgramacion\\ProyectoProgramacion1\\src\\proyectoprogramacion1\\copiar.png"));
        pas.setText(null);
        pas.setIcon(new ImageIcon("C:\\Users\\Manuel\\Documents\\NetBeansProjects\\ProyectoProgramacion\\ProyectoProgramacion1\\src\\proyectoprogramacion1\\pegar.png"));

        Save.setEnabled(false);
        SaveAs.setEnabled(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        area.addKeyListener(k1);
        setTitle(currentFile);
        setVisible(true);

        //LABEL1
        JLabel label1 = new JLabel("Zanoni&Moroncini ©");
        label1.setBounds(10, 30, 100, 30);
        label1.setForeground(new Color(25, 114, 236));
        label1.setToolTipText("Trabajo realizado por Moroncini y Zanoni");
        tool.add(label1);

        //COLOR CHOOSER 
        JButton btnColor = new JButton("Color de Fuente");
        JMB.add(btnColor);
//        btnColor.setBounds(10, 30, 125, 20);
        btnColor.setToolTipText("Clickea para desplegar la paleta de colores");
        btnColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abre la paleta de colores
                Color colores = JColorChooser.showDialog(tool, "Eligir un color para la fuente", Color.BLACK);
                //Cambia el color del boton para saber que color estamos usando
                btnColor.setBackground(colores);
//                btnColor.setForeground(WHITE);
                area.setForeground(colores);
                label1.setForeground(colores);
            }
        });

    }

    private KeyListener k1 = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            changed = true;
            Save.setEnabled(true);
            SaveAs.setEnabled(true);
        }

    };
    Action Open = new AbstractAction("Abrir", new ImageIcon("open.gif")) {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveOld();
            if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                readInFile(dialog.getSelectedFile().getAbsolutePath());
            }
            SaveAs.setEnabled(true);
        }
    };

    Action Save = new AbstractAction("Guardar", new ImageIcon("")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!currentFile.equals("Untitled")) {
                saveFile(currentFile);
            } else {
                saveFileAs();
            }
        }

    };
    Action SaveAs = new AbstractAction("Guardar como...") {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveFileAs();
        }
    };
    Action Quit = new AbstractAction("Salir") {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveOld();
            System.exit(0);
        }
    };
    Action New = new AbstractAction("Nuevo", new ImageIcon("")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (saveOld()) {
                area.setText("");
                currentFile = "Untitled";
                setTitle(currentFile);
                changed = false;
                Save.setEnabled(false);
                SaveAs.setEnabled(false);
            }

        }
    };
    ActionMap m = area.getActionMap();
    Action Cut = m.get(DefaultEditorKit.cutAction);
    Action Copy = m.get(DefaultEditorKit.copyAction);
    Action Paste = m.get(DefaultEditorKit.pasteAction);

    private void saveFileAs() {
        if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            saveFile(dialog.getSelectedFile().getAbsolutePath());
        }
    }

    private Boolean saveOld() {
        if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            saveFile(dialog.getSelectedFile().getAbsolutePath());
            return true;
        }
        return false;

    }

    private void readInFile(String fileName) {
        try {
            FileReader r = new FileReader(fileName);
            area.read(r, null);
            r.close();
            currentFile = fileName;
            setTitle(currentFile);
            changed = false;
        } catch (IOException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "El editor no pudo encontrar el archivo: " + fileName);

        }

    }

    private void saveFile(String fileName) {
        try {
            FileWriter w = new FileWriter(fileName);
            area.write(w);
            w.close();
            currentFile = fileName;
            setTitle(currentFile);
            changed = false;
            Save.setEnabled(true);
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        new ProyectoProgramacion1();
    }
}
