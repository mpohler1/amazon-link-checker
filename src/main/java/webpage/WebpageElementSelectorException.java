package webpage;

public class WebpageElementSelectorException extends RuntimeException {

    public WebpageElementSelectorException(String link) {
        super("WebpageElementChecker could not check elements on " + link);
    }
}
