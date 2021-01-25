package commandline;

public class HelpOptionFoundException extends CommandLineHandlerException {

    public HelpOptionFoundException() {
        super("help option found");
    }
}
