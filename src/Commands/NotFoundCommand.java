package Commands;

import ServerConection.ServerThread;

public class NotFoundCommand implements iCommand{

    @Override
    public  String getCommandName(){

        return "NotFound Command";
    }

    @Override
    public void execute(String[] args, ServerThread serverThread){

        serverThread.sendMessage("Command not found");
    }
}
