package Commands;

import ServerConection.ServerUser;
import org.json.simple.JSONObject;

public class SelectCharacterCommand implements iCommand{

    @Override
    public String getCommandName(){

        return "SelectCharacter Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection){

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",5);

        if(args.length<3 || args.length>3){
            new ErrorCommand().execute(args,userConnection);
        }else{

            if(userConnection.setActiveCharacter(args[2])){

                jsonOutput.put("RequestState",true);
                //jsonOutput.put("Character",userConnection.getActiveCharacter().)
            }else{

                jsonOutput.put("RequestState",false);
            }
        }
        jsonOutput.put("Command",this.getCommandName());
        jsonOutput.put("Message","New Character Selected");

        userConnection.getConnection().writeOutput(jsonOutput.toJSONString());
    }
}
