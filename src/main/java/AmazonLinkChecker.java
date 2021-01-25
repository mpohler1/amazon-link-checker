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

import java.util.Date;

public class AmazonLinkChecker {

    private static final CommandLineHandler COMMAND_LINE_HANDLER = new ApacheCommandLineHandler();
    private static final MailService MAIL_SERVICE = new MailAPIService();
    private static final String AMAZON_ADD_TO_CART_BUTTON_ID = "add-to-cart-button";

    public static void main(String[] args) {
        try {
            TaskModel taskModel = COMMAND_LINE_HANDLER.parseCommandLine(args);
            doTask(taskModel);
        } catch (CommandLineHandlerException e) {
            e.printStackTrace();
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
            System.out.println("An add to cart button was found on " + new Date().toString());
            MAIL_SERVICE.sendMail(
                    taskModel.getEmailSenderName(),
                    taskModel.getEmailSenderAddress(),
                    taskModel.getProductName() + " is back in stock.\n\n" + taskModel.getProductPageLink()
            );
            System.out.println("Sent email on " + new Date().toString());
        } else {
            System.out.println("No add to cart button was found on " + new Date().toString());
        }
    }
}
