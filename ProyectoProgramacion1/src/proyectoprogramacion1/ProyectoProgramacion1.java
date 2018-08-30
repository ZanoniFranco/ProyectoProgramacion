package proyectoprogramacion1;

import com.ozten.font.JFontChooser;
import java.awt.*;
//import java.awt.Color;
//import java.awt.Font;
//import static java.awt.Color.*;
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

import java.util.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.text.DefaultEditorKit;

public class ProyectoProgramacion1 extends JFrame {

    private JTextArea area = new JTextArea(20, 80);
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
    private JLabel label2;
    private KeyListener k1;

    //archivo en cuestión
    private File currentFile = null;

    //si se realiza un cambio en el documento, pasa a ser true
    private boolean changed = false;

    private Action save;
    private Action saveAs;
    private Action newFile;
    private Action open;
    JMenu file;
    JMenuBar JMB;
    JMenu edit;

    //Inicio constructor.
    public ProyectoProgramacion1() {

        area.setFont(new Font("Monospaced", 0, 12));
        area.setToolTipText("Hoja");
        JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setToolTipText("Sube y baja");
        add(scroll, BorderLayout.CENTER);

        JMB = new JMenuBar();
        setJMenuBar(JMB);
        file = new JMenu("Archivo");
        edit = new JMenu("Editar");
        JMB.setToolTipText("Barra de tareas");
        JMB.add(file);
        JMB.add(edit);
        file.setToolTipText("Archivo");
        edit.setToolTipText("Editar");

        JToolBar tool = new JToolBar();
        add(tool, BorderLayout.NORTH);

        ActionMap m = area.getActionMap();
        Action Cut = m.get(DefaultEditorKit.cutAction);
        Action Copy = m.get(DefaultEditorKit.copyAction);
        Action Paste = m.get(DefaultEditorKit.pasteAction);

        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);

        edit.getItem(0).setText("Cortar");
        edit.getItem(0).setToolTipText("Ctrl + X");
        edit.getItem(1).setText("Copiar");
        edit.getItem(1).setToolTipText("Ctrl + C");
        edit.getItem(2).setText("Pegar");
        edit.getItem(2).setToolTipText("Ctrl + V");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        setTitle("Editor de Texto");
        setVisible(true);

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

        //LABEL1
        JLabel label1 = new JLabel("Zanoni&Moroncini ©");
        tool.add(label1);
        label1.setBounds(10, 30, 100, 30);
        label1.setForeground(new Color(25, 114, 236));
        label1.setToolTipText("Trabajo realizado por Moroncini y Zanoni");

        //LABEL 2
        label2 = new JLabel("Untitled");
        tool.add(label2);
        label2.setBounds(30, 30, 100, 30);
        label2.setForeground(new Color(0, 0, 0));
        label2.setToolTipText("Archivo actual");

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
                area.setForeground(colores);
                label1.setForeground(colores);
            }
        });

        //FUENTE
        JButton btnFont = new JButton("Tipo de Fuente");
        JMB.add(btnFont);
        btnFont.setToolTipText("Clickea para editar la fuente");
        btnFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFontChooser fc = new JFontChooser();
                JOptionPane.showMessageDialog(null, fc, "Edita la fuente", JOptionPane.PLAIN_MESSAGE);
                area.setFont(fc.getPreviewFont());
//                btnFont.setFont(fc.getPreviewFont());
//                label1.setFont(fc.getPreviewFont());
            }
        });

        //"Guardar" dentro del JMB archivo
        save = new AbstractAction("Guardar", new ImageIcon("")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentFile.equals(null)) {
                    saveFile(currentFile);
                } else {
                    saveFileAs();
                }
            }

        };

        //"Guardar como" dentro del JMB archivo
        saveAs = new AbstractAction("Guardar como...") {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFileAs();
            }
        };

        //activa y desactiva el save y saveAs dependiendoe si se ha hecho un cambio o no
        k1 = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changed = true;
                save.setEnabled(true);
                saveAs.setEnabled(true);
            }

        };

        area.addKeyListener(k1);

        //"Salir" dentro del JMB archivo
        Action Quit = new AbstractAction("Salir") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuestaQuit;
                respuestaQuit = JOptionPane.showConfirmDialog(null,
                        "¿Desea guardar antes de SALIR?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuestaQuit == JOptionPane.YES_OPTION) {
                    saveOld();
                    System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        };

        //"Nuevo" dentro del JMB archivo
        newFile = new AbstractAction("Nuevo", new ImageIcon("")) {

            @Override
            public void actionPerformed(ActionEvent e) {
                int respuestaNew;
                respuestaNew = JOptionPane.showConfirmDialog(null,
                        "¿Desea guardar antes de CREAR un nuevo archivo?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuestaNew == JOptionPane.YES_OPTION) {
                    saveOld();
                    area.setText("");
                    currentFile = null;
                    label2.setText("Untitled");
                    changed = false;
                    save.setEnabled(false);
                    saveAs.setEnabled(false);
                } else {
                    area.setText("");
                    currentFile = null;
                    label2.setText("Untitled");
                    changed = false;
                    save.setEnabled(false);
                    saveAs.setEnabled(false);
                }
            }

        };

        save.setEnabled(
                false);
        saveAs.setEnabled(
                false);

        Action open = new AbstractAction("Abrir", new ImageIcon("")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuestaOpen;
                int dialogOpen;
                respuestaOpen = JOptionPane.showConfirmDialog(null,
                        "¿Desea guardar antes de ABRIR un nuevo archivo?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuestaOpen == JOptionPane.YES_OPTION) {
                    saveOld();
                    changed = false;
                    save.setEnabled(false);
                    saveAs.setEnabled(false);
                    dialogOpen = dialog.showOpenDialog(null);
                    readInFile(dialog.getSelectedFile());
                } else {
                    changed = false;
                    save.setEnabled(false);
                    saveAs.setEnabled(false);
                    dialogOpen = dialog.showOpenDialog(null);
                    readInFile(dialog.getSelectedFile());
                }
                saveAs.setEnabled(true);
            }

        };

        file.add(newFile);

        file.add(open);

        file.add(save);

        file.add(Quit);

        file.add(saveAs);

        file.addSeparator();

        
        tool.add(newFile)
                .setToolTipText("Nuevo");

        tool.add(open)
                .setToolTipText("Abrir");

        tool.add(save)
                .setToolTipText("Guardar");

        tool.addSeparator();

        for (int i = 0;
                i < 4; i++) {
            file.getItem(i).setIcon(null);
        }

    } //Fin constructor

    private void saveFileAs() {
        if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            saveFile(dialog.getSelectedFile());
        }
    }

    private Boolean saveOld() {
        if (dialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            saveFile(dialog.getSelectedFile());
            return true;
        }
        return false;

    }

    private void readInFile(File fileName) {
        try {
            FileReader r = new FileReader(fileName.getAbsolutePath());
            area.read(r, null);
            r.close();
            currentFile = fileName;
            label2.setText(fileName.getName());
            changed = false;
        } catch (IOException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "El editor no pudo encontrar el archivo: " + fileName);

        }

    }

    private void saveFile(File fileName) {
        try {
            FileWriter w = new FileWriter(fileName);
            area.write(w);
            w.close();
            currentFile = fileName;
            label2.setText(fileName.getName());
            changed = false;
            save.setEnabled(true);
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        ProyectoProgramacion1 proyectoProgramacion1 = new ProyectoProgramacion1();
    }
}
