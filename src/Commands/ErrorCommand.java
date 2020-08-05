package Commands;

import ServerConection.ServerUser;
import ServerConection.UserConnection;
import org.json.simple.JSONObject;

public class ErrorCommand implements iCommand{

    @Override
    public String getCommandName(){

      return "Error Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection){

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",5);
        jsonOutput.put("Command",this.getCommandName());
        jsonOutput.put("Message","Error in command");

        userConnection.getConnection().writeOutput(jsonOutput.toJSONString());
    }
}
