package mail;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MailAPIService implements MailService {
    private static final String API_URL = "https://api.masonpohler.com:9000/sendMail";

    public void sendMail(String name, String email, String body) throws MailServiceException {
        try {
            StringEntity mailModel = buildMailModel(name, email, body);
            HttpPost post = buildHttpPost(mailModel);
            executePost(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new MailServiceException(e.getMessage());
        }
    }

    private StringEntity buildMailModel(String name, String email, String body) throws UnsupportedEncodingException {
        String mailModelJsonString = new JSONObject()
                .put("productName", name)
                .put("email", email)
                .put("body", body)
                .toString();

        return new StringEntity(mailModelJsonString);
    }

    private HttpPost buildHttpPost(StringEntity mailModel) {
        HttpPost post = new HttpPost(API_URL);
        post.setEntity(mailModel);
        post.setHeader("content-type", "application/json");
        return post;
    }

    private void executePost(HttpPost post) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(post);
        StatusLine statusLine = response.getStatusLine();

        if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
    }
}
