package ServerConection;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainServerTest {

    private int port=9090;
    private String hostName="localhost";

    public void initClient(){

        try{
            //Creates socket connection
            Socket socket=new Socket(this.hostName,this.port);

            new ReadThread(socket,this).start();
            new WriteThread(socket,this).start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //*** Inner Classes ***
    private class ReadThread extends Thread{

        private Socket socket;
        private MainServerTest client;
        private BufferedReader reader;

        //*** Constructor ***
        public ReadThread(Socket socket, MainServerTest client){

            this.socket = socket;
            this. client = client;

            try{

                InputStream inputStream = socket.getInputStream();
                this.reader = new BufferedReader(new InputStreamReader(inputStream));

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public void run(){
            while(true){

                try{

                    String serverResponse = reader.readLine();
                    System.out.println(serverResponse+"\n");

                }catch (IOException e){
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private class WriteThread extends Thread{

        private Socket socket;
        private MainServerTest client;
        private PrintWriter writer;

        //*** Constructor ***
        public WriteThread(Socket socket, MainServerTest client){

            this.socket = socket;
            this. client = client;

            try{

                OutputStream outputStream = socket.getOutputStream();
                this.writer = new PrintWriter(outputStream,true);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public void run(){

            Scanner scanner=new Scanner(System.in);
            String userInput;

            do{

                userInput = scanner.nextLine();
                writer.println(userInput);


            }while (!userInput.equals("exit"));

            try {

                this.socket.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){
        MainServerTest client=new MainServerTest();
        client.initClient();
    }
}
