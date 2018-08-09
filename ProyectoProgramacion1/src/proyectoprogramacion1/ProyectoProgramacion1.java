package proyectoprogramacion1;
    
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProyectoProgramacion1 {

    public static void main(String[] args) {
        //FRAME
        JFrame frame = new JFrame("Manu's Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(null);
 
//CHECKBOX
//variableName.setBounds(position X, position Y, size Width, size Wigh);
        JCheckBox cb1 = new JCheckBox("Copiar Arriba");
        cb1.setBounds(130, 415, 120, 15);
        cb1.setSelected(false);
        cb1.setToolTipText("Envía el texto arriba");
        frame.add(cb1);

//LABEL1
        JLabel label1 = new JLabel("Manu Moroncini ©");
        label1.setBounds(380, 350, 200, 200);
        label1.setForeground(new Color(25, 114, 236));
        label1.setToolTipText("Manu Moroncini, todos los derechos reservados");
        frame.add(label1);

//TEXT AREA
        JTextArea txtarea = new JTextArea();
        txtarea.setBounds(20, 20, 460, 290);
        txtarea.setToolTipText("Aquí se muestra el texto que usted ingresa");
        txtarea.setEditable(false);
        frame.add(txtarea);

//SCROLL PANE
        JScrollPane scroll = new JScrollPane(txtarea);
        scroll.setBounds(20, 20, 460, 280);
        scroll.setToolTipText("Sube y baja");
        frame.add(scroll);

//DETALLES
//hacer que las palabras no se corten
        txtarea.setLineWrap(true);
        txtarea.setWrapStyleWord(true);
//fuente        
        Font fuente = new Font("Consolas", Font.BOLD, 30);
        txtarea.setFont(fuente);
        txtarea.setBackground(Color.orange);

//TEXT FIELD
        JTextField txtfield = new JTextField();
        txtfield.setBounds(20, 320, 460, 30);
        txtfield.setToolTipText("Ingrese texto aquí");
        frame.add(txtfield);
                  
//JComboBox
    JComboBox combbox = new JComboBox();
    combbox.setBounds(20,1,100,20);
    combbox.addItem("uno");
    combbox.addItem("dos");
    frame.add(combbox);
    
   combbox.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent ae) {
       txtarea.setFront(new java.awt.Font("Tahoma", 1, 11));
   }
});


//BOTON BORRAR
        JButton btnBorrar = new JButton("BORRAR");
        btnBorrar.setBounds(20, 370, 100, 30);
        btnBorrar.setToolTipText("Borra todo el texto");
        frame.add(btnBorrar);

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int respuesta;
                respuesta = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de que desea borrar TODO el texto?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    txtarea.setText("");
                    txtfield.setText("");
                }
            }
        });

//BOTON ENVIAR        
//con Click
        JButton btnEnviar = new JButton("ENVIAR");
        btnEnviar.setBounds(130, 370, 100, 30);
        btnEnviar.setToolTipText("Envía el texto escrito");

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (cb1.isSelected()) {
                    txtarea.setText(txtfield.getText() + "\n" + txtarea.getText());
                } else {
                    txtarea.setText(txtarea.getText() + "\n" + txtfield.getText());
                }
                txtfield.setText("");
            }
        });

//con Enter     
        txtfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (cb1.isSelected()) {
                        txtarea.setText(txtfield.getText() + "\n" + txtarea.getText());
                    } else {
                        txtarea.setText(txtarea.getText() + "\n" + txtfield.getText());
                    }
                    txtfield.setText("");
                }
            }
        });
        frame.add(btnEnviar);

//BOTON SALIR
        JButton btnSalir = new JButton("Log Out");
        btnSalir.setBounds(240, 370, 100, 30);
        btnSalir.setToolTipText("Cierra la sesión actual");
        frame.add(btnSalir);
        
//Hacer la ventana/frame visible.
        frame.setVisible(true);
    }
}
    

