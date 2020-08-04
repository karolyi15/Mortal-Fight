package Commands;

import ServerConection.UserConnection;

public class ErrorCommand implements iCommand{

    @Override
    public String getCommandName(){

      return "Error Command";
    }

    @Override
    public void execute(String[] args, UserConnection userConnection){

        //userConnection.sendMessage("Error in command");
    }
}
