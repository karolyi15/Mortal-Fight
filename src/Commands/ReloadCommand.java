package Commands;

import ServerConection.ConnectionState;
import ServerConection.ServerUser;
import org.json.simple.JSONObject;

public class ReloadCommand implements iCommand{

    @Override
    public  String getCommandName(){

        return "Reload Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection){

        if(userConnection.getConnection().getConnectionState()== ConnectionState.PLAYING) {
            if (userConnection.canReload()) {

                userConnection.reload();

            } else {

                new ErrorCommand().execute(args, userConnection);
            }
        }else{
            new ErrorCommand().execute(args, userConnection);
        }
    }
}
