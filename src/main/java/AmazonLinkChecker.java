import commandline.ApacheCommandLineHandler;
import commandline.CommandLineHandler;
import commandline.CommandLineHandlerException;
import mail.MailAPIService;
import mail.MailService;
import mail.MailServiceException;
import model.TaskModel;
import webpage.JsoupWebpageElementChecker;
import webpage.WebpageElementChecker;
import webpage.WebpageElementSelectorException;

public class AmazonLinkChecker {

    private static final CommandLineHandler COMMAND_LINE_HANDLER = new ApacheCommandLineHandler();
    private static final MailService MAIL_SERVICE = new MailAPIService();
    private static final String AMAZON_ADD_TO_CART_BUTTON_ID = "add-to-cart-button";

    public static void main(String[] args) {
        try {
            TaskModel taskModel = COMMAND_LINE_HANDLER.parseCommandLine(args);
            doTask(taskModel);
        } catch (CommandLineHandlerException e) {
            COMMAND_LINE_HANDLER.printHelp();
        } catch (WebpageElementSelectorException e) {
            e.printStackTrace();
        } catch (MailServiceException e) {
            e.printStackTrace();
        }
    }

    private static void doTask(TaskModel taskModel) throws WebpageElementSelectorException, MailServiceException {
        WebpageElementChecker elementChecker = new JsoupWebpageElementChecker();

        // If the Amazon product page has an 'add to cart' button, then the product is in stock
        if (elementChecker.elementDoesExist(taskModel.getProductPageLink(), AMAZON_ADD_TO_CART_BUTTON_ID)) {
            MAIL_SERVICE.sendMail(
                    taskModel.getEmailSenderName(),
                    taskModel.getEmailSenderAddress(),
                    taskModel.getProductName() + " is back in stock.\n\n" + taskModel.getProductPageLink()
            );
        } else {
            // System.out.println("No add to cart button");
        }
    }
}
