package Commands;

import ServerConection.ServerThread;

public class ErrorCommand implements iCommand{

    @Override
    public String getCommandName(){

      return "Error Command";
    }

    @Override
    public void execute(String[] args, ServerThread serverThread){

        serverThread.sendMessage("Error in command");
    }
}
