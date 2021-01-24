package webpage;

public class WebpageElementSelectorException extends RuntimeException {

    public WebpageElementSelectorException(String link) {
        super("WebpageElementSelector could not select elements from " + link);
    }
}
