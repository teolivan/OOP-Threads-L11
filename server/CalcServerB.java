package L11.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerB {
    private Calculator calculator;

    public CalcServerB(Calculator calculator, int port) {
        this.calculator = calculator;
        new Connection(port).start();
    }

    private class Connection extends Thread {
        private int port;

        public Connection(int port) {
            this.port = port;
        }

        public void run() {
            Socket socket = null;
            System.out.println("Server B startad");
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (true) {
                    try {
                        socket = serverSocket.accept();
                        new ClientHandler(socket);
                    } catch (IOException e) {
                        System.err.println(e);
                        if (socket != null) socket.close();
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
            }
            System.out.println("Server stoppad");
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            start();
        }

        public void run() {
            String[] parts;
            String request, response;
            double nbr1, nbr2, answer;
            char operation;
            try {
                while (true) {
                    request = dis.readUTF();
                    parts = request.split(",");
                    if (parts.length == 3) {
                        try {
                            nbr1 = Double.parseDouble(parts[0]);
                            nbr2 = Double.parseDouble(parts[1]);
                            operation = parts[2].charAt(0);
                            answer = calculator.calculate(nbr1, nbr2, operation);
                            response = answer + "\n" + parts[0] + parts[2] + parts[1] + "=" + answer;
                        } catch (Exception e) {
                            response = e.toString() + "\n" + parts[0] + parts[2] + parts[1];
                        }
                    } else {
                        response = "Fel antal argument: " + parts.length;
                    }
                    dos.writeUTF(response);
                    dos.flush();
                }
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (Exception e2) {
                }
            }
            System.out.println("Klient nerkopplad");
        }
    }

    public static void main(String[] args) {
        new CalcServerB(new Calculator(), 722);
    }
}
