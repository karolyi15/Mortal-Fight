package Commands;

import ServerConection.ServerUser;
import ServerConection.UserConnection;

public interface iCommand {

    public String getCommandName();
    public void execute(String[] args, ServerUser userConnection);
}
