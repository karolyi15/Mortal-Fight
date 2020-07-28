package Commands;

import ServerConection.ServerThread;

public interface iCommand {

    public String getCommandName();
    public void execute(String[] args, ServerThread serverThread);
}
