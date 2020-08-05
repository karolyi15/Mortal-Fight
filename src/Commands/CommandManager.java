package Commands;

import java.util.HashMap;

public class CommandManager {

    //********************************************************************************************************//
    //********************************************* CLASS FIELDS *********************************************//

    private static CommandManager commandManager;
    private final HashMap<String, Class<? extends iCommand>> commands;

    //********************************************************************************************************//
    //******************************************** CLASS METHODS *********************************************//

    //*** Constructor ***
    private CommandManager(){

        this.commands = new HashMap();
        this.commands.put("message",MessageCommand.class);
        this.commands.put("selectCharacter",SelectCharacterCommand.class);
        this.commands.put("attack",AttackCommand.class);

    }

    public static CommandManager getInstance(){

       if( commandManager == null){

           commandManager = new CommandManager();
       }

       return commandManager;
    }

    //*** Commands Management ***
    public iCommand getCommand(String commandName){

        if(this.commands.containsKey(commandName)){

            try {

                return this.commands.get(commandName).getDeclaredConstructor().newInstance();

            }catch (Exception e){

                return new ErrorCommand();
            }
        }else{
            return  new NotFoundCommand();
        }

    }

    public void installCommand(String commandName,Class<? extends iCommand> newCommand){

        this.commands.put(commandName, newCommand);
    }
}
