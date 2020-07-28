package ServerConection;

import Commands.iCommand;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ServerThread extends Thread{

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private Server server;
    private Socket socket;
    private PrintWriter writer;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    public ServerThread(Socket socket, Server server) {
        this.socket=socket;
        this.server=server;
    }


    public void run(){

        try {
            //************** Read data to client ****************//

            //Read data from the client (read to byte array)
            InputStream input = socket.getInputStream();
            //Set byte array to string
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //************** Send data to client ***************//

            //Write data as a byte array
            OutputStream output = socket.getOutputStream();
            //Converts byte array to text format
            this.writer = new PrintWriter(output, true);

            String inputData;


            do {

                inputData = reader.readLine();
                //Print on Server Console
                System.out.print(inputData+"\n");

                //Execute Command
                String[] tokenizedCommand = this.tokenizer(inputData);
                iCommand command = this.server.getCommandManager().getCommand(tokenizedCommand[1]);
                command.execute(tokenizedCommand,this);


            } while (!inputData.equals("bye"));


            //*********** Close client connection *************//
            //Server close socket connection but still work for other sockets
            socket.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] tokenizer(String input){

        return input.split("\\s+");
    }

    public void sendMessage(String message){
        this.writer.println(message);
    }

    public void broadcast(String message){
        this.server.broadcast(message,this);
    }

}
