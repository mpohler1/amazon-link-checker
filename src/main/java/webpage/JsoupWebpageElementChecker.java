package webpage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupWebpageElementChecker implements WebpageElementChecker {

    public boolean elementDoesExist(String link, String elementId) throws WebpageElementSelectorException {
        try {
            Elements elements = getElementsWithElementId(link, elementId);
            return !elements.isEmpty();
        } catch (IOException e) {
            throw new WebpageElementSelectorException(link);
        }
    }

    private Elements getElementsWithElementId(String link, String elementId) throws IOException {
        Document document = Jsoup.connect(link).get();
        return document.select("#" + elementId);
    }

}
