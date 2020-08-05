package Commands;

import ServerConection.ServerUser;
import ServerConection.UserConnection;
import org.json.simple.JSONObject;

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
    public void execute(String[] args, ServerUser userConnection){

        String[] tokenizedMessage = Arrays.copyOfRange(args,2,args.length);
        String message="";
        for(int token = 0; token<tokenizedMessage.length;token++){
            message+= " "+tokenizedMessage[token];
        }

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",5);
        jsonOutput.put("Command",this.getCommandName());
        jsonOutput.put("Message",args[0]+message);

        userConnection.getConnection().getGame().broadcast(jsonOutput.toJSONString());

    }

}
