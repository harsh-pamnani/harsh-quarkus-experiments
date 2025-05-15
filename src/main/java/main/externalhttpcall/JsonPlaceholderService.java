package main.externalhttpcall;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class JsonPlaceholderService {

    @ConfigProperty(name = "JSONP_PLACEHOLDER_HOST", defaultValue = " ")
    String jsonPlaceholderHost;

    private final OkHttpClient httpClient;

    public JsonPlaceholderService() {
        this.httpClient = new OkHttpClient();
    }

    public String makeDummyJsonPlaceholderCall() throws IOException {
        Request request = new Request.Builder().url(jsonPlaceholderHost).get().build();

        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected error occurred while calling AppSync mutation " + response);
        }

        return response.body().string();
    }
}
