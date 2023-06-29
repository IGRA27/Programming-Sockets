/*
package Fuente;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerSocket serverSocket;
    private int port;

    public SocketServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Servidor iniciado en el puerto " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.start();
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String message = in.readLine();
                System.out.println("Mensaje recibido del cliente: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
IGUAL LO DE ARRIBA ES CUANDO PROBABA, ABAJO REFACTORIZADO
*/
package Fuente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerSocket serverSocket;
    private int port;

    public SocketServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Servidor iniciado en el puerto " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.start();
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        }

        public void run() {
            try {
                String message = in.readLine();
                System.out.println("Mensaje recibido del cliente: " + message);

                // Aquí puedes procesar el mensaje y enviar una respuesta
                String response = "Respuesta del servidor";
                out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            SocketServer server = new SocketServer(5000);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
