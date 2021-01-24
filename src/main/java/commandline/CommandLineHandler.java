package commandline;

import model.TaskModel;

public interface CommandLineHandler {
    TaskModel parseCommandLine(String[] args) throws CommandLineHandlerException;
    void printHelp();
}
