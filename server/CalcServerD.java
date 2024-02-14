package L11.server;

import L11.Calculation;
import L11.Expression;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerD {
    private Calculator calculator;

    public CalcServerD(Calculator calculator, int port) {
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
            System.out.println("Server D startad");
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
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            start();
        }

        public void run() {
            Expression exp;
            double answer;
            try {
                while (true) {
                    try {
                        exp = (Expression) ois.readObject();
                        answer = calculator.calculate(exp.getNbr1(), exp.getNbr2(), exp.getOperation());
                        oos.writeObject(new Calculation(answer, exp));
                        oos.flush();
                    } catch (ClassNotFoundException e) {
                    }
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
        new CalcServerD(new Calculator(), 724);
    }
}
