package Commands;

import GameFactory.WeaponType;
import ServerConection.ConnectionState;
import ServerConection.ServerUser;
import org.json.simple.JSONObject;

public class AttackCommand implements iCommand{

    @Override
    public String getCommandName(){

        return "Attack Command";
    }

    @Override
    public void execute(String[] args, ServerUser userConnection) {


        if (userConnection.getConnection().getConnectionState() == ConnectionState.PLAYING) {

            if (args.length < 3 || args.length > 3) {
                new ErrorCommand().execute(args, userConnection);
                return;
            } else {

                try {
                    if (userConnection.getActiveCharacter().getState() && userConnection.getActiveCharacter().getWeapon(WeaponType.valueOf(args[2])).getState()) {

                        //Calculate Damage
                        int damage = userConnection.getActiveCharacter().attack(WeaponType.valueOf(args[2]), userConnection.getConnection().getGame().getEnemy(userConnection).getActiveCharacter());
                        userConnection.getConnection().getGame().getEnemy(userConnection).getActiveCharacter().takeDamage(damage);

                        //Update Character Stats
                        JSONObject updater = new JSONObject();
                        updater.put("Request", 5);
                        updater.put("Command", "SelectCharacter Command");
                        updater.put("RequestState", true);
                        updater.put("Character", userConnection.getConnection().getGame().getEnemy(userConnection).getActiveCharacter().toJson());

                        userConnection.getConnection().getGame().getEnemy(userConnection).getConnection().writeOutput(updater.toJSONString());

                        //Send Message
                        String message = args[0] + " attacked " + userConnection.getConnection().getGame().getEnemy(userConnection).getActiveCharacter().getCharacterType().toString() + " with " + userConnection.getActiveCharacter().getCharacterType().toString();
                        new MessageCommand().execute(userConnection.getConnection().getGame().tokenizer(message), userConnection);

                        //Check Game Over
                        //userConnection.getConnection().getGame().isGameOver();
                        //Change Turn
                        userConnection.getConnection().getGame().changeTurn(userConnection);

                    }else{
                        new ErrorCommand().execute(args, userConnection);
                        return;
                    }
                }catch (Exception e){
                    new ErrorCommand().execute(args, userConnection);
                    return;
                }
            }


        }else{
            new ErrorCommand().execute(args, userConnection);
            return;

        }
    }

}
