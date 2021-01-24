import model.TaskModel;
import options.OptionTypes;
import options.OptionsFactory;
import org.apache.commons.cli.*;
import webpage.JsoupWebpageElementChecker;
import webpage.WebpageElementChecker;
import webpage.WebpageElementSelectorException;

public class AmazonLinkChecker {

    private static final CommandLineParser PARSER = new DefaultParser();
    private static final Options HELP_ONLY_OPTIONS = OptionsFactory.createOptions(OptionsFactory.HELP_ONLY);
    private static final Options ALL_OPTIONS = OptionsFactory.createOptions(OptionsFactory.ALL);
    private static final String HELP_STRING = "amazon-link-checker -n <product name> -l <product link>";
    private static final String AMAZON_ADD_TO_CART_BUTTON_ID = "add-to-cart-button";

    public static void main(String[] args) {
        try {
            checkForHelpOption(args);
            TaskModel taskModel = buildTaskModelFromCommandLine(args);
            doTask(taskModel);
        } catch (ParseException e) {
            printHelp();
        } catch (WebpageElementSelectorException e) {
            e.printStackTrace();
        }
    }

    private static void checkForHelpOption(String[] args) throws ParseException {
        CommandLine commandLine = PARSER.parse(HELP_ONLY_OPTIONS, args);

        if (commandLine.hasOption(OptionTypes.HELP_OPTION)) {
            printHelp();
        }
    }

    private static TaskModel buildTaskModelFromCommandLine(String[] args) throws ParseException {
        TaskModel taskModel = new TaskModel();
        CommandLine commandLine = PARSER.parse(ALL_OPTIONS, args);

        taskModel.setName(commandLine.getOptionValue(OptionTypes.NAME_OPTION));
        taskModel.setLink(commandLine.getOptionValue(OptionTypes.LINK_OPTION));

        if (commandLine.hasOption(OptionTypes.EMAIL_SENDER_NAME_OPTION)) {
            taskModel.setEmailSenderName(commandLine.getOptionValue(OptionTypes.EMAIL_SENDER_NAME_OPTION));
        }

        if (commandLine.hasOption(OptionTypes.EMAIL_SENDER_ADDRESS_OPTION)) {
            taskModel.setEmailSenderAddress(commandLine.getOptionValue(OptionTypes.EMAIL_SENDER_ADDRESS_OPTION));
        }

        return taskModel;
    }

    private static void doTask(TaskModel taskModel) throws WebpageElementSelectorException {
        WebpageElementChecker elementChecker = new JsoupWebpageElementChecker();

        if (elementChecker.elementDoesExist(taskModel.getLink(), AMAZON_ADD_TO_CART_BUTTON_ID)) {

        }
    }

    private static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(HELP_STRING, ALL_OPTIONS);
        System.exit(0);
    }
}
