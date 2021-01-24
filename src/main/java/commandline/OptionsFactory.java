package commandline;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsFactory {
    public static final int HELP_ONLY = 0;
    public static final int ALL = 1;

    public static Options createOptions(int mode) {
        switch (mode) {
            case HELP_ONLY:
                return createHelpOnlyOptions();

            case ALL:
            default:
                return createAllOptions();
        }
    }

    private static Options createHelpOnlyOptions() {
        Option helpOption = createOptionalArgumentlessOption(
                OptionTypes.HELP_OPTION,
                OptionTypes.HELP_OPTION_VERBOSE,
                "Displays usage information"
        );

        Options options = new Options();
        options.addOption(helpOption);

        return options;
    }

    private static Options createAllOptions() {
        Option helpOption = createOptionalArgumentlessOption(
                OptionTypes.HELP_OPTION,
                OptionTypes.HELP_OPTION_VERBOSE,
                "Displays usage information"
        );
        Option nameOption = createRequiredOption(
                OptionTypes.NAME_OPTION,
                OptionTypes.NAME_OPTION_VERBOSE,
                "product name",
                "The name of the product on the Amazon product page"
        );
        Option linkOption = createRequiredOption(
                OptionTypes.LINK_OPTION,
                OptionTypes.LINK_OPTION_VERBOSE,
                "product link",
                "The link to the Amazon product page"
        );
        Option emailSenderNameOption = createOptionalOption(
                OptionTypes.EMAIL_SENDER_NAME_OPTION,
                OptionTypes.EMAIL_SENDER_NAME_OPTION_VERBOSE,
                "email name",
                "The name of the email sender"
        );
        Option emailSenderAddressOption = createOptionalOption(
                OptionTypes.EMAIL_SENDER_ADDRESS_OPTION,
                OptionTypes.EMAIL_SENDER_ADDRESS_OPTION_VERBOSE,
                "email address",
                "The address of the email sender"
        );

        Options options = new Options();
        options.addOption(helpOption);
        options.addOption(nameOption);
        options.addOption(linkOption);
        options.addOption(emailSenderNameOption);
        options.addOption(emailSenderAddressOption);

        return options;
    }

    private static Option createRequiredOption(String optionName, String longOptionName, String argName, String description) {
        return Option.builder(optionName)
                .longOpt(longOptionName)
                .required()
                .hasArg()
                .argName(argName)
                .desc(description)
                .build();
    }

    private static Option createOptionalOption(String optionName, String longOptionName, String argName, String description) {
        return Option.builder(optionName)
                .longOpt(longOptionName)
                .hasArg()
                .argName(argName)
                .desc(description)
                .build();
    }

    private static Option createOptionalArgumentlessOption(String optionName, String longOptionName, String description) {
        return Option.builder(optionName)
                .longOpt(longOptionName)
                .desc(description)
                .build();
    }
}
