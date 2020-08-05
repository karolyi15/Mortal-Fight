package Commands;

import ServerConection.ServerUser;
import org.json.simple.JSONObject;

public class TieCommand implements iCommand{

    @Override
    public String getCommandName(){

        return "Tie Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection){

        if(args.length>2){

            new ErrorCommand().execute(args,userConnection);

        }else{


            userConnection.setTie(true);

            if(userConnection.getConnection().getGame().getEnemy(userConnection).getTie()){
                //Its Tie
               userConnection.getConnection().getGame().surrender(userConnection);

            }else{
                String message =args[0]+"proposes x a tie";
                String[] tokenizedMessage = userConnection.getConnection().getGame().tokenizer(message);
                new MessageCommand().execute(tokenizedMessage,userConnection);
            }


        }
    }
}
