package Commands;

import ServerConection.ServerUser;
import org.json.simple.JSONObject;

public class SurrenderCommand implements iCommand{

    @Override
    public  String getCommandName(){

        return "Surrender Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection){

        userConnection.getConnection().getGame().surrender(userConnection);
    }
}
