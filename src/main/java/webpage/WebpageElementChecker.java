package webpage;

import java.util.List;

public interface WebpageElementChecker {
    boolean elementDoesExist(String link, String elementId) throws WebpageElementSelectorException;
}
