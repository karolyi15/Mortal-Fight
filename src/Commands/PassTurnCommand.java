package Commands;

import ServerConection.ServerUser;
import org.json.simple.JSONObject;

public class PassTurnCommand implements iCommand {

    @Override
    public String getCommandName(){

        return "PassTurn Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection){

        if(args.length>2){

            new ErrorCommand().execute(args,userConnection);
        }else{


            userConnection.getConnection().getGame().changeTurn(userConnection);
        }
    }
}
