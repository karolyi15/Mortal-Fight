package Commands;

import ServerConection.UserConnection;

public class NotFoundCommand implements iCommand{

    @Override
    public  String getCommandName(){

        return "NotFound Command";
    }

    @Override
    public void execute(String[] args, UserConnection userConnection){

        //userConnection.sendMessage("Command not found");
    }
}
