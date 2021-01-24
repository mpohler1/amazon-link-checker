import options.OptionTypes;
import options.OptionsFactory;
import org.apache.commons.cli.*;

public class AmazonLinkChecker {

    public static void main(String[] args) {
        try {
            CommandLineParser parser = new DefaultParser();
            Options helpOnlyOptions = OptionsFactory.createOptions(OptionsFactory.HELP_ONLY);
            Options allOptions = OptionsFactory.createOptions(OptionsFactory.ALL);
            CommandLine commandLine = parser.parse(helpOnlyOptions, args);

            if (commandLine.hasOption(OptionTypes.HELP_OPTION) || commandLine.hasOption(OptionTypes.HELP_OPTION_VERBOSE)) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("amazon-link-checker", allOptions);
                System.exit(0);
            }

            commandLine = parser.parse(allOptions, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
