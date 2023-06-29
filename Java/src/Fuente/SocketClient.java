/*
package Fuente;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
//import java.util.Scanner;

public class SocketClient {

    private Socket socket;
    private PrintWriter out;

    public SocketClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String message) {
        out.println(message);
    }

    public void close() throws IOException {
        socket.close();
    }
}
CODIGO DE ARRIBA ERA CUANDO ESTABA PROGRAMANDO Y NO SME SALIA
*/
package Fuente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public SocketClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String message) throws IOException {
        out.println(message);
    }

    public String receive() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            SocketClient client = new SocketClient("localhost", 5000);
            client.send("Hola, servidor");

            String response = client.receive();
            System.out.println("Respuesta recibida del servidor: " + response);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

