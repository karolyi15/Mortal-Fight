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
                System.out.println("Game Over");

            }else{
                String message =args[0]+"message proposes a tie";
                String[] tokenizedMessage = userConnection.getConnection().getGame().tokenizer(message);
                new MessageCommand().execute(tokenizedMessage,userConnection);
            }


        }
        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("Request",5);
        jsonOutput.put("Command",this.getCommandName());
        jsonOutput.put("Message","Error in command");

        userConnection.getConnection().writeOutput(jsonOutput.toJSONString());
    }
}
