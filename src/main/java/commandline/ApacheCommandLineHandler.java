package commandline;

import model.TaskModel;
import org.apache.commons.cli.*;

public class ApacheCommandLineHandler implements CommandLineHandler{

    private static final CommandLineParser PARSER = new DefaultParser();
    private static final Options HELP_ONLY_OPTIONS = OptionsFactory.createOptions(OptionsFactory.HELP_ONLY);
    private static final Options ALL_OPTIONS = OptionsFactory.createOptions(OptionsFactory.ALL);
    private static final String HELP_STRING = "amazon-link-checker -n <product name> -l <product page link>";

    public TaskModel parseCommandLine(String[] args) throws CommandLineHandlerException {
        try {
            checkForHelpOption(args);
            return buildTaskModelFromCommandLine(args);
        } catch (ParseException e) {
            throw new CommandLineHandlerException(e.getMessage());
        } catch (HelpOptionFoundException e) {
            throw e; // this block is here to be clear that this exception can come from checkForHelpOption
        }
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(HELP_STRING, ALL_OPTIONS);
    }

    private void checkForHelpOption(String[] args) throws ParseException, HelpOptionFoundException {
        CommandLine commandLine = PARSER.parse(HELP_ONLY_OPTIONS, args);

        if (commandLine.hasOption(OptionTypes.HELP_OPTION)) {
            throw new HelpOptionFoundException();
        }
    }

    private TaskModel buildTaskModelFromCommandLine(String[] args) throws ParseException {
        TaskModel taskModel = new TaskModel();
        CommandLine commandLine = PARSER.parse(ALL_OPTIONS, args);

        taskModel.setProductName(commandLine.getOptionValue(OptionTypes.NAME_OPTION));
        taskModel.setProductPageLink(commandLine.getOptionValue(OptionTypes.LINK_OPTION));

        if (commandLine.hasOption(OptionTypes.EMAIL_SENDER_NAME_OPTION)) {
            taskModel.setEmailSenderName(commandLine.getOptionValue(OptionTypes.EMAIL_SENDER_NAME_OPTION));
        }

        if (commandLine.hasOption(OptionTypes.EMAIL_SENDER_ADDRESS_OPTION)) {
            taskModel.setEmailSenderAddress(commandLine.getOptionValue(OptionTypes.EMAIL_SENDER_ADDRESS_OPTION));
        }

        return taskModel;
    }
}
