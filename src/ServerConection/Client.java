package ServerConection;

import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;


public class Client {

    private int port = 9090;
    private String hostName = "localhost";
    private PrintWriter writer;

    public void initClient(TextArea outputField) {

        try {

            //Creates socket connection
            Socket socket = new Socket(this.hostName, this.port);

            //Creates reader thread
            new ReadThread(socket, this, outputField).start();

            //Creates writer system
            OutputStream outputStream = socket.getOutputStream();
            this.writer = new PrintWriter(outputStream, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOutput(String message){

        this.writer.println(message);

    }

    //*** Inner Classes ***
    private class ReadThread extends Thread {

        private Socket socket;
        private Client client;
        private BufferedReader reader;
        private TextArea output;

        //*** Constructor ***
        public ReadThread(Socket socket, Client client, TextArea output) {

            this.socket = socket;
            this.client = client;
            this.output = output;

            try {

                InputStream inputStream = socket.getInputStream();
                this.reader = new BufferedReader(new InputStreamReader(inputStream));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            while (true) {

                try {

                    String serverResponse = reader.readLine();
                    this.output.appendText(serverResponse + "\n");

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }


}




