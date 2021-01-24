package webpage;

import java.util.List;

public interface WebpageElementSelector {
    List<Object> selectElementsById(String link, String elementId) throws WebpageElementSelectorException;
}
