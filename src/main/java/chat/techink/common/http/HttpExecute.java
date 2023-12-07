package chat.techink.common.http;


import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author xujianxing
 * @date 2023年11月25日 23:36
 */
public class HttpExecute {

    protected OkHttpClient client;


    /**
     * @param url
     * @param headers
     * @param restfulResponseHandler
     * @param <T>
     * @return
     */
    public <T> T executeGet(String url, Headers headers, RestfulResponseHandler<T> restfulResponseHandler) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            requestBuilder.headers(headers);
        }
        Request request = requestBuilder.build();
        try {

            Response response = client.newCall(request).execute();
            int status = response.code();
            String responseBody = response.body().string();
            if (restfulResponseHandler.success(status)) {
                return restfulResponseHandler.handle(response.code(), responseBody);
            } else {
                restfulResponseHandler.errorHandle(response.code(), responseBody);
            }
        } catch (Exception e) {
            restfulResponseHandler.errorHandle(e);
        }
        return null;
    }
}