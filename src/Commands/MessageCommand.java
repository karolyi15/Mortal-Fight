package Commands;

import ServerConection.ServerThread;

import java.util.Arrays;

public class MessageCommand implements iCommand{

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    @Override
    public String getCommandName(){

        return "Message Command";
    }

    @Override
    public void execute(String[] args, ServerThread serverThread){

        String[] tokenizedMessage = Arrays.copyOfRange(args,2,args.length);
        String message="";
        for(int token = 0; token<tokenizedMessage.length;token++){
            message+= " "+tokenizedMessage[token];
        }

        serverThread.broadcast(args[0]+message);
    }

}
