package Commands;

import ServerConection.ConnectionState;
import ServerConection.ServerUser;
import org.json.simple.JSONObject;

public class SelectCharacterCommand implements iCommand{

    @Override
    public String getCommandName(){

        return "SelectCharacter Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection) {

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request", 5);

        if (userConnection.getConnection().getConnectionState() == ConnectionState.PLAYING) {

            if (args.length < 3 || args.length > 3) {
                new ErrorCommand().execute(args, userConnection);
                return;
            } else {

                if (userConnection.setActiveCharacter(args[2])) {

                    jsonOutput.put("RequestState", true);
                    jsonOutput.put("Character",userConnection.getActiveCharacter().toJson());
                } else {

                    jsonOutput.put("RequestState", false);
                }
            }
            jsonOutput.put("Command", this.getCommandName());

            userConnection.getConnection().writeOutput(jsonOutput.toJSONString());

        }else{
            new ErrorCommand().execute(args, userConnection);
            return;

        }
    }
}
