package Interfaz;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Fuente.SocketClient;

public class CustomJFrame extends JFrame {  
    private JTextField textField;
    private SocketClient client;

    public CustomJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setBounds(100, 100, 450, 300); 
        getContentPane().setLayout(null); 

        JButton btnSend = new JButton("Enviar");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                try {
                    if (client == null) { 
                        client = new SocketClient("localhost", 5000); 
                    }
                    client.send(message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSend.setBounds(335, 227, 89, 23);
        getContentPane().add(btnSend);

        textField = new JTextField();
        textField.setBounds(10, 228, 315, 20);
        getContentPane().add(textField);
        textField.setColumns(10);
    }
}



