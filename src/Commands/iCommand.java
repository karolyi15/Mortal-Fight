package Commands;

import ServerConection.UserConnection;

public interface iCommand {

    public String getCommandName();
    public void execute(String[] args, UserConnection userConnection);
}
