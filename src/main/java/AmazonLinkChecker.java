import commandline.ApacheCommandLineHandler;
import commandline.CommandLineHandler;
import commandline.CommandLineHandlerException;
import model.TaskModel;
import webpage.JsoupWebpageElementChecker;
import webpage.WebpageElementChecker;
import webpage.WebpageElementSelectorException;

public class AmazonLinkChecker {

    private static final CommandLineHandler COMMAND_LINE_HANDLER = new ApacheCommandLineHandler();
    private static final String AMAZON_ADD_TO_CART_BUTTON_ID = "add-to-cart-button";

    public static void main(String[] args) {
        try {
            TaskModel taskModel = COMMAND_LINE_HANDLER.parseCommandLine(args);
            doTask(taskModel);
        } catch (CommandLineHandlerException e) {
            COMMAND_LINE_HANDLER.printHelp();
        } catch (WebpageElementSelectorException e) {
            e.printStackTrace();
        }
    }

    private static void doTask(TaskModel taskModel) throws WebpageElementSelectorException {
        WebpageElementChecker elementChecker = new JsoupWebpageElementChecker();

        if (elementChecker.elementDoesExist(taskModel.getLink(), AMAZON_ADD_TO_CART_BUTTON_ID)) {

        }
    }
}
