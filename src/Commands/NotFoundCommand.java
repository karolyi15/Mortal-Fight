package Commands;

import ServerConection.Server;
import ServerConection.ServerUser;
import ServerConection.UserConnection;
import org.json.simple.JSONObject;

public class NotFoundCommand implements iCommand{

    @Override
    public  String getCommandName(){

        return "NotFound Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection){


        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",5);
        jsonOutput.put("Command",this.getCommandName());
        jsonOutput.put("Message","Not Found Command");

        userConnection.getConnection().writeOutput(jsonOutput.toJSONString());
    }
}
