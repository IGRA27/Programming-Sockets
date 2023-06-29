package Fuente;
import java.awt.EventQueue;
import Interfaz.CustomJFrame;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomJFrame frame = new CustomJFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

//Para leer solo en consola:
/*
package Fuente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SocketClient client = null;

        while (true) {
            try {
                System.out.println("Ingresa un mensaje para enviar:");
                String message = reader.readLine();

                if (client == null) {
                    client = new SocketClient("localhost", 5000);
                }
                client.send(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}




//CODIGO MAL PORQUE NO USE GUI USE UN CUSTOM:
/*
package Fuente;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
*/