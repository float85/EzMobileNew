package mobile.fpts.com.privatefpts.common;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {
    OkHttpClient client;

    public String getHttpClient(String link) throws IOException {
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(link)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
