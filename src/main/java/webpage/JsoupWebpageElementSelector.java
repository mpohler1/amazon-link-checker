package webpage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JsoupWebpageElementSelector implements WebpageElementSelector {

    @Override
    public List<Object> selectElementsById(String link, String elementId) throws WebpageElementSelectorException {
        try {
            Elements elements = getElementsWithElementId(link, elementId);
            return Collections.singletonList(elements);
        } catch (IOException e) {
            throw new WebpageElementSelectorException(link);
        }
    }

    private Elements getElementsWithElementId(String link, String elementId) throws IOException {
        Document document = Jsoup.connect(link).get();
        return document.select("#" + elementId);
    }
}
